class RenderableAsHtmlElement {
  renderAsHtmlElement() {
    throw new Error("This method must be overrided by child class.");
  }
}

class Profile extends RenderableAsHtmlElement {
  constructor(githubAPIresponse) {
    super();
    this.imageUrl = githubAPIresponse.avatar_url;
    this.profileUrl = githubAPIresponse.html_url;
    this.chipData = {};
    this.chipData.repos = githubAPIresponse.public_repos;
    this.chipData.gists = githubAPIresponse.public_gists;
    this.chipData.followers = githubAPIresponse.followers;
    this.chipData.following = githubAPIresponse.following;
    this.bioData = {};
    this.bioData.company = githubAPIresponse.company;
    this.bioData.website = githubAPIresponse.blog;
    this.bioData.location = githubAPIresponse.location;
    this.bioData.createdAt = githubAPIresponse.created_at;
  }

  renderAsHtmlElement() {
    const profileElement = document.createElement("div");
    profileElement.id = "profile";
    profileElement.classList.add("container");
    profileElement.appendChild(this.createAvatarElement());
    profileElement.appendChild(this.createInfoElement());
    document.querySelector("main").appendChild(profileElement);
  }

  createAvatarElement() {
    const avatarElement = document.createElement("div");
    avatarElement.id = "avatar";
    avatarElement.appendChild(this.createAvatarImageElement());
    avatarElement.appendChild(this.createViewProfileButtonElement());
    return avatarElement;
  }

  createAvatarImageElement() {
    const imageElement = document.createElement("img");
    imageElement.src = this.imageUrl;
    return imageElement;
  }

  createViewProfileButtonElement() {
    const profileLinkElement = document.createElement("a");
    profileLinkElement.href = this.profileUrl;
    const buttonElement = document.createElement("button");
    buttonElement.innerText = "View Profile";
    profileLinkElement.appendChild(buttonElement);
    return profileLinkElement;
  }

  createInfoElement() {
    const infoElement = document.createElement("div");
    infoElement.id = "info";
    infoElement.appendChild(this.createStatsElement());
    infoElement.appendChild(this.createBioElement());
    return infoElement;
  }

  createStatsElement() {
    const statsElement = document.createElement("ul");
    statsElement.id = "stats";
    Object.keys(this.chipData).forEach((key) => {
      statsElement.appendChild(this.createChipElement(key));
    });
    return statsElement;
  }

  createChipElement(chipName) {
    const chipElement = document.createElement("li");
    chipElement.id = chipName;
    chipElement.classList.add("chip");
    chipElement.innerText = `Public ${
      chipName.charAt(0).toUpperCase() + chipName.slice(1)
    } : ${this.chipData[chipName]}`;
    return chipElement;
  }

  createBioElement() {
    const bioElement = document.createElement("ul");
    bioElement.classList.add("container");
    bioElement.id = "bio";
    Object.keys(this.bioData).forEach((key) => {
      bioElement.appendChild(this.createBioItemElement(key));
    });
    return bioElement;
  }

  createBioItemElement(bioName) {
    const bioItemElement = document.createElement("li");
    bioItemElement.innerText = `${
      bioName.charAt(0).toUpperCase() + bioName.slice(1)
    } : ${this.bioData[bioName]}`;
    return bioItemElement;
  }
}

class Repository extends RenderableAsHtmlElement {
  constructor(githubAPIresponse) {
    super();
    this.repoName = githubAPIresponse.name;
    this.link = githubAPIresponse.html_url;
    this.chipData = {};
    this.chipData.watchers = githubAPIresponse.watchers_count;
    this.chipData.stars = githubAPIresponse.stargazers_count;
    this.chipData.forks = githubAPIresponse.forks_count;
  }

  renderAsHtmlElement() {
    const repositoryElement = document.createElement("li");
    repositoryElement.classList.add("container", "repo");
    repositoryElement.appendChild(this.createRepositoryNameElement());
    this.createRepositoryChipElements().forEach((element) =>
      repositoryElement.appendChild(element)
    );
    document.querySelector("#repository").appendChild(repositoryElement);
  }

  createRepositoryNameElement() {
    const repositoryNameElement = document.createElement("a");
    repositoryNameElement.innerText = this.repoName;
    repositoryNameElement.href = this.link;
    return repositoryNameElement;
  }

  createRepositoryChipElements() {
    return Object.keys(this.chipData).reduce((acc, key) => {
      const chipElement = document.createElement("span");
      chipElement.classList.add("chip");
      chipElement.id = `${key}`;
      chipElement.innerText = `${
        key.charAt(0).toUpperCase() + key.slice(1)
      } : ${this.chipData[key]}`;
      acc.push(chipElement);
      return acc;
    }, new Array());
  }
}

const GITHUB_API_URL = "https://api.github.com";

window.onload = () => {
  const input = document.querySelector("#searchbar input");
  input.addEventListener("keyup", async (event) => {
    if (event.code === "Enter") {
      const name = event.target.value;
      try {
        const profile = new Profile(await fetchGithubUser(name));
        const repos = (await fetchGithubRepos(name)).map(
          (repo) => new Repository(repo)
        );
        createFinderElements(profile, repos);
      } catch (e) {
        console.error(e);
      }
    }
  });
};

const fetchGithubUser = async (name) => {
  const response = await fetch(`${GITHUB_API_URL}/users/${name}`);
  if (response.status >= 400) {
    return null;
  }
  const body = await response.json();
  return body;
};

const fetchGithubRepos = async (name) => {
  const response = await fetch(`${GITHUB_API_URL}/users/${name}/repos`);
  if (response.status >= 400) {
    return null;
  }
  const body = await response.json();
  return body;
};

const createFinderElements = (profile, repos) => {
  initFinderElements();
  profile.renderAsHtmlElement();
  createRepositoryContainerElement();
  repos.forEach((repo) => repo.renderAsHtmlElement());
};

const initFinderElements = () => {
  try {
    document.querySelector("#profile").remove();
    document.querySelector("#repository").remove();
  } catch (e) {}
};

const createRepositoryContainerElement = () => {
  const repositoryContainerElement = document.createElement("ul");
  repositoryContainerElement.id = "repository";
  const repositoryContainerHeaderElement = document.createElement("h1");
  repositoryContainerHeaderElement.innerText = "Last Repos";
  repositoryContainerElement.appendChild(repositoryContainerHeaderElement);
  document.querySelector("main").appendChild(repositoryContainerElement);
};
