import { useEffect, useState } from "react";
import { Button, ListGroup } from "react-bootstrap";
import axiosInstance from "../components/api/axiosInstance";

interface Product {
  id: number;
  name: string;
  price: number;
}

const Cart = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const username = localStorage.getItem("username");

  const getProductsFromCart = async () => {
    try {
      const token = localStorage.getItem("token");
      console.log("token: " + token);
      console.log("username: " + username);
      const response = await axiosInstance.get<Product[]>(
        `/cartItem?username=${username}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      console.log(response.data);

      setProducts(response.data);
      console.log(products);
    } catch (error) {
      console.log(error);
    }
  };

  const removeOneProductFromList = (id: number) => {
    // Find the index of the last occurrence of the id
    const lastIndex = products.map(product => product.id).lastIndexOf(id);
    
    // If the id exists, remove it
    if (lastIndex !== -1) {
      // Create a new array excluding the last occurrence
      const updatedProducts = [
        ...products.slice(0, lastIndex), // Items before the last occurrence
        ...products.slice(lastIndex + 1), // Items after the last occurrence
      ];

      // Update state with the new array
      setProducts(updatedProducts);
    }

  }

  const handleOnRemoveFromCart = async (id: number) => {
    const username = localStorage.getItem("username");
    try {
      const token = localStorage.getItem("token");
      console.log("token: " + token);
      console.log("username: " + username);
      const response = await axiosInstance.delete(
        `/cartItem/${username}/${id}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      console.log(response.data);
      removeOneProductFromList(id);
    } catch (error) {
      console.log(error);
    }
  };



  useEffect(() => {
    getProductsFromCart();
  }, []);

  return (
    <>
      <ListGroup>
        {products.map((product, index) => (
          <ListGroup.Item key={index}>
            <p>Name: {product.name}</p>
            <p>Price: {product.price}</p>
            <Button onClick = {() => handleOnRemoveFromCart(product.id)}>
                Remove From Cart
            </Button>
          </ListGroup.Item>
        ))}
      </ListGroup>
    </>
  );
};

export default Cart;
