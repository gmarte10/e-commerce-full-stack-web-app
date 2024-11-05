import { Button, Container, ListGroup, Nav, Navbar } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import axiosInstance from "../../components/api/axiosInstance";
import { useEffect, useState } from "react";

interface Product {
  id: number;
  name: string;
  price: number;
  description: string;
  image: string;
  imageData?: string;
}

const AdminHome = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const navigate = useNavigate();
  const getProductsFromCart = async () => {
    try {
      const token = localStorage.getItem("token");
      console.log("token: " + token);
      const response = await axiosInstance.get<Product[]>(`/products`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      console.log(response.data);
      const productsWithImg = await Promise.all(
        response.data.map(async (product) => {
          const imageData = await getImages(product.image);
          return { ...product, imageData };
        })
      );

      setProducts(productsWithImg);
      // console.log(products);
    } catch (error) {
      console.log(error);
    }
  };

  const getImages = async (image: string) => {
    try {
      const token = localStorage.getItem("token");
      const response = await axiosInstance.get(`/images/${image}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
        responseType: "blob",
      });
      const base64Data = await new Promise<string>((resolve, reject) => {
        const reader = new FileReader();
        reader.onloadend = () => resolve(reader.result as string);
        reader.onerror = reject;
        reader.readAsDataURL(response.data);
      });
      return base64Data;
    } catch (error) {
      console.log(error);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    navigate("/login");
  };

  useEffect(() => {
    getProductsFromCart();
  }, []);

  const handleRemoveProduct = async (id: number) => {
    try {
      const token = localStorage.getItem("token");
      const response = await axiosInstance.delete(`/products/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      console.log(response.data);
      getProductsFromCart();
    } catch (error) {
      console.log(error);
    }
  };

  const handleAddProduct = () => {
    navigate("/addProduct");
  }

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
              <Button onClick={handleLogout}>Logout</Button>
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
            <img src={product.imageData} alt={product.image} />
            <Button onClick={() => handleRemoveProduct(product.id)}>
              Remove Product
            </Button>
          </ListGroup.Item>
        ))}
      </ListGroup>
      <Button onClick={handleAddProduct}>Add a product</Button>
    </>
  );
};

export default AdminHome;
