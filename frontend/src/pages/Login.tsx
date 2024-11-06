import { useState } from "react";
import { Button, Form } from "react-bootstrap";
import axiosInstance from "../components/api/axiosInstance";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const loginAccount = async () => {
    try {
      const response = await axiosInstance.post(`/login`, {
        username: username,
        password: password,
      });
      // console.log("Before setting: " + localStorage.getItem("token"));
      localStorage.setItem("token", response.data);
      localStorage.setItem("username", username);
      // console.log("After setting: " + localStorage.getItem("token"));
      const response2 = await axiosInstance.get(`/user/info/${username}`, {
        headers: {
          Authorization: `Bearer ${response.data}`,
        },
      });
      console.log(response2.data);
      const role = response2.data[0];
      const id = response2.data[1];
      console.log("ROLE: " + role);
      console.log("ID: " + id);
      localStorage.setItem("account_id", id);

      if (role === 1) {
        navigate("/adminHome");
      }
      else {
        navigate("/home");
      }
      // console.log(response.data);
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
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Email</Form.Label>
          <Form.Control
            type="email"
            placeholder="Enter email"
            value={username}
            onChange={handleUsernameChange}
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Password"
            value={password}
            onChange={handlePasswordChange}
          />
        </Form.Group>
        <Button variant="primary" type="submit">
          Login
        </Button>
      </Form>
    </>
  );
};

export default Login;
