import { useState } from "react";
import { Button, Form } from "react-bootstrap";
import axiosInstance from "../components/api/axiosInstance";
import { useNavigate } from "react-router-dom";
import CustomFormGroup from "../components/CustomFormGroup";

const Register = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const navigate = useNavigate();

  const registerAccount = async () => {
    try {
      const response = await axiosInstance.post(`/register`, {
        name: name,
        username: username,
        password: password,
      });
      console.log(response.data);
      navigate("/login");
    } catch (error) {
      console.log(error);
    }
  };

  const handleUsernameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  const handleNameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setName(e.target.value);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    registerAccount();
  };

  return (
    <>
      <Form onSubmit={handleSubmit}>
        <CustomFormGroup
          label="You Name"
          type="text"
          value={name}
          handleChange={handleNameChange}
          placeholder="Name"
          controlId="formBasicName"
        />
        <CustomFormGroup
          label="Email"
          type="email"
          value={username}
          handleChange={handleUsernameChange}
          placeholder="Enter email"
          controlId="formBasicEmail"
        />
        <CustomFormGroup
          label="Password"
          type="password"
          value={password}
          handleChange={handlePasswordChange}
          placeholder="Password"
          controlId="formBasicPassword"
        />
        <Button variant="primary" type="submit">
          Register
        </Button>
      </Form>
    </>
  );
};

export default Register;
