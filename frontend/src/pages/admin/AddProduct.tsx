import { useState } from "react";
import { Button } from "react-bootstrap";
import { Form } from "react-bootstrap";
import axiosInstance from "../../components/api/axiosInstance";
import { useNavigate } from "react-router-dom";

const AddProduct = () => {
    const [name, setName] = useState("");
    const [price, setPrice] = useState("");
    const [description, setDescription] = useState("");
    const [image, setImage] = useState("");
    const navigate = useNavigate();

    const handleNameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setName(e.target.value);
    }

    const handlePriceChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPrice(e.target.value);
    }

    const handleDescriptionChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setDescription(e.target.value);
    }

    const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setImage(e.target.value);
    }

    const uploadProduct = async () => {
        const token = localStorage.getItem("token");
        try {
            const response = await axiosInstance.post(`/products`, {
                name: name,
                price: price,
                description: description,
                image: image,
            }, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            console.log(response.data);
            navigate("/adminHome");
        
        } catch (error) {
            console.log(error);
        }
    }

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        uploadProduct();
    }

    return (<>
     <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3" controlId="formBasicProductName">
          <Form.Label>Product Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Name"
            value={name}
            onChange={handleNameChange}
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicPrice">
          <Form.Label>Product Price</Form.Label>
          <Form.Control
            type="text"
            placeholder="Price"
            value={price}
            onChange={handlePriceChange}
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicDescription">
          <Form.Label>Product Description</Form.Label>
          <Form.Control
            type="text"
            placeholder="Description"
            value={description}
            onChange={handleDescriptionChange}
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicImage">
          <Form.Label>Product Image</Form.Label>
          <Form.Control
            type="text"
            placeholder="Image"
            value={image}
            onChange={handleImageChange}
          />
        </Form.Group>
        <Button variant="primary" type="submit">
          Upload Product
        </Button>
      </Form>
    </>);
}
 
export default AddProduct;