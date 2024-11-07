import { useState } from "react";
import { Button, Form } from "react-bootstrap";
import axiosInstance from "../components/api/axiosInstance";
import { useNavigate } from "react-router-dom";
import CustomFormGroup from "../components/CustomFormGroup";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const loginAccount = async () => {
    try {
      const loginResponse = await axiosInstance.post(`/login`, {
        username: username,
        password: password,
      });
      localStorage.setItem("token", loginResponse.data);
      localStorage.setItem("username", username);

      const infoResponse = await axiosInstance.get(`/user/info/${username}`, {
        headers: {
          Authorization: `Bearer ${loginResponse.data}`,
        },
      });
      console.log(infoResponse.data);
      const role = infoResponse.data[0];
      const id = infoResponse.data[1];
      console.log("ROLE: " + role);
      console.log("ID: " + id);
      localStorage.setItem("account_id", id);

      if (role === 1) {
        navigate("/adminHome");
      } else {
        navigate("/home");
      }
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

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    loginAccount();
  };

  return (
    <>
      <Form onSubmit={handleSubmit}>
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
          Login
        </Button>
      </Form>
    </>
  );
};

export default Login;
