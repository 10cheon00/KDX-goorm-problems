import { useEffect, useState } from "react";
import { getAuth, createUserWithEmailAndPassword } from "firebase/auth";
import { useNavigate } from "react-router-dom";
import Form from "../../../components/form/Form";

const SignUp = () => {
  const [firebaseError, setFirebaseError] = useState("");
  const [validationMessage, setValidationMessage] = useState("");
  const auth = getAuth();

  const navigator = useNavigate();

  const register = (email: string, password: string) => {
    createUserWithEmailAndPassword(auth, email, password)
      .then((userCredential) => {
        navigator("/login");
      })
      .catch((error) => {
        setFirebaseError("올바른 정보를 입력해주세요.");
      });
  };

  return (
    <Form
      title={"회원가입"}
      getDataForm={register}
      firebaseError={firebaseError}
    />
  );
};

export default SignUp;
