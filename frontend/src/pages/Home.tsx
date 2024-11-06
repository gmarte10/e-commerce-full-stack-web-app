import { Button, Container, ListGroup, Nav, Navbar } from "react-bootstrap";
import axiosInstance from "../components/api/axiosInstance";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import useLogout from "../hooks/useLogout";
import useGetImage from "../hooks/useGetImage";

interface Product {
  id: number;
  name: string;
  price: number;
  description: string;
  image: string;
  imageData?: string | null;
}

const Home = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const navigate = useNavigate();
  const logout = useLogout();
  const getImage = useGetImage();

  const getProducts = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axiosInstance.get<Product[]>(`/products`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      const productsWithImg = await Promise.all(
        response.data.map(async (product) => {
          const imageData = await getImage(product.image);
          return { ...product, imageData };
        })
      )
      setProducts(productsWithImg);
      console.log(productsWithImg);
    } catch (error) {
      console.log(error);
    }
  };

  const handleAddToCart = async (id: number) => {
    const username = localStorage.getItem("username");
    try {
      const token = localStorage.getItem("token");
      const response = await axiosInstance.post(
        `/cartItem/${username}/${id}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      console.log(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  const handleGoToCart = () => {
    navigate("/cart");
  };

  useEffect(() => {
    getProducts();
  }, []);
  
  return (
    <>
      <Navbar expand="lg" className="bg-body-tertiary">
        <Container>
          <Navbar.Brand href="#home">
            E-Commerce-Full-Stack-Web-App
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Button onClick={logout}>Logout</Button>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
      <ListGroup>
        {products.map((product) => (
          <ListGroup.Item key={product.id}>
            <p>Name: {product.name}</p>
            <p>Price: {product.price}</p>
            <p>Description: {product.description}</p>
            {product.imageData && <img src={product.imageData} alt={product.image} />}
            <Button onClick={() => handleAddToCart(product.id)}>
              Add To Cart
            </Button>
          </ListGroup.Item>
        ))}
      </ListGroup>
      <Button onClick={handleGoToCart}>Go To Cart</Button>
    </>
  );
};

export default Home;
