import { FC, useState } from "react";

import styles from "./Form.module.scss";

type FormProps = {
  title: string;
  getDataForm: (email: string, password: string) => void;
  firebaseError: string;
};

type Inputs = {
  email: string;
  password: string;
};

const Form: FC<FormProps> = ({ title, getDataForm, firebaseError }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  return (
    <form className={styles.form}>
      <div>
        <input
          placeholder="Email"
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
      </div>
      <div>
        <input
          placeholder="Password"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>
      <button
        onClick={(event) => {
          event.preventDefault();
          getDataForm(email, password);
        }}
      >
        {title}
      </button>
      {firebaseError !== "" && (
        <p className={styles.form_error}>{firebaseError}</p>
      )}
    </form>
  );
};

export default Form;
