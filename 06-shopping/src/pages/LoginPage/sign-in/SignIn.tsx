import { getAuth, signInWithEmailAndPassword } from "firebase/auth";
import app from "../../../firebase";
import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { save } from "../../../store/user/user.slice";
import Form from "../../../components/form/Form";

const SignIn = () => {
  const auth = getAuth(app);
  const location = useLocation();
  const navigator = useNavigate();
  const dispatch = useDispatch();
  const [firebaseError, setFirebaseError] = useState("");

  const saveUserAndRedirect = (res) => {
    const user = {
      email: res.user.email,
      token: res.user.accessToken,
      uid: res.user.uid,
    };
    dispatch(save(user));

    const queryParams = new URLSearchParams(location.search);
    const redirectURL = queryParams.get("redirect");

    if (redirectURL === null) {
      navigator("/", { replace: true });
    } else {
      navigator(redirectURL, { replace: true });
    }
  };

  const signIn = (email: string, password: string) => {
    signInWithEmailAndPassword(auth, email, password)
      .then(saveUserAndRedirect)
      .catch((err) => {
        setFirebaseError("로그인 정보가 잘못되었습니다.");
      });
  };

  return (
    <Form title="로그인" getDataForm={signIn} firebaseError={firebaseError} />
  );
};

export default SignIn;
