import { Button, Container, ListGroup, Nav, Navbar } from "react-bootstrap";
import axiosInstance from "../components/api/axiosInstance";
import { useEffect, useState } from "react";
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

const Orders = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const username = localStorage.getItem("username");
  const logout = useLogout();
  const getImage = useGetImage();

  const getProductsFromCart = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axiosInstance.get<Product[]>(
        `/orderItem?username=${username}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      console.log(response.data);
      const productsWithImg = await Promise.all(
        response.data.map(async (product) => {
          const imageData = await getImage(product.image);
          return { ...product, imageData };
        })
      );
      setProducts(productsWithImg);
      console.log(productsWithImg);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getProductsFromCart();
  }, []);

  return (
    <>
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
          {products.map((product, index) => (
            <ListGroup.Item key={index}>
              <p>Name: {product.name}</p>
              <p>Price: {product.price}</p>
              <p>Description: {product.description}</p>
              {product.imageData && (
                <img src={product.imageData} alt={product.image} />
              )}
            </ListGroup.Item>
          ))}
        </ListGroup>
      </>
    </>
  );
};

export default Orders;
