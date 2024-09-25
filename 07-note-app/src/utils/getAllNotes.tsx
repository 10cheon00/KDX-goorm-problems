import { NoteCard } from "../components"
import { FilterType } from "../store/filter/filterSlice";
import { NotesContainer } from "../styles/styles"
import { Note } from "../types/note"

const filteredNotes = (notes: Note[], filter: typeof FilterType[keyof typeof FilterType]) => {

    const lowPriority = notes.filter(({ priority }) => priority === "low");
    const highPriority = notes.filter(({ priority }) => priority === "high");

    if (filter === FilterType.PRIORITY_LOW) {
        return [...lowPriority, ...highPriority];
    } else if (filter === FilterType.PRIORITY_HIGH) {
        return [...highPriority, ...lowPriority];
    } else if (filter === FilterType.LATEST) {
        return notes.sort((a, b) => b.createdTime - a.createdTime);
    } else if (filter === FilterType.CREATED) {
        return notes.sort((a, b) => a.createdTime - b.createdTime);
    } else if (filter === FilterType.EDITED) {
        const editedNotes = notes.filter(({ editedTime }) => editedTime);
        const normalNotes = notes.filter(({ editedTime }) => !editedTime);

        const sortEdited = editedNotes.sort((a, b) => ((b.editedTime as number) - (a.editedTime as number)));
        return [...sortEdited, ...normalNotes];
    } else {
        return notes;
    }

}




const getAllNotes = (mainNotes: Note[], filter: string) => {

    const pinned = mainNotes.filter(({ isPinned }) => isPinned);
    const normal = mainNotes.filter(({ isPinned }) => !isPinned);

    if (normal.length !== 0 && pinned.length === 0) {
        return (
            <>
                <div className="allNotes__notes-type">
                    All Notes <span>({normal.length})</span>
                </div>
                <NotesContainer>
                    {filteredNotes(normal, filter).map((note) => (
                        <NoteCard key={note.id} note={note} type="notes" />
                    ))}
                </NotesContainer>
            </>
        )
    }

    if (pinned.length !== 0 && normal.length === 0) {
        return (
            <>
                <div className="allNotes__notes-type">
                    Pinned Notes <span>({pinned.length})</span>
                </div>
                <NotesContainer>
                    {filteredNotes(pinned, filter).map((note) => (
                        <NoteCard key={note.id} note={note} type="notes" />
                    ))}
                </NotesContainer>
            </>
        )
    }

    if (pinned.length !== 0 && normal.length !== 0) {
        return (
            <>
                <div>
                    <div className="allNotes__notes-type">
                        Pinned Notes <span>({pinned.length})</span>
                    </div>
                    <NotesContainer>
                        {filteredNotes(pinned, filter).map((note) => (
                            <NoteCard key={note.id} note={note} type="notes" />
                        ))}
                    </NotesContainer>
                </div>
                <div>
                    <div className="allNotes__notes-type">
                        All Notes <span>({normal.length})</span>
                    </div>
                    <NotesContainer>
                        {filteredNotes(normal, filter).map((note) => (
                            <NoteCard key={note.id} note={note} type="notes" />
                        ))}
                    </NotesContainer>
                </div>
            </>
        )
    }
}

export default getAllNotes;