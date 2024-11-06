import { useState } from "react";
import { Button } from "react-bootstrap";
import { Form } from "react-bootstrap";
import axiosInstance from "../../components/api/axiosInstance";
import { useNavigate } from "react-router-dom";
import CustomFormGroup from "../../components/CustomFormGroup";

// Find a way to simplify attributes and their handleChanges
const AddProduct = () => {
    const [name, setName] = useState("");
    const [price, setPrice] = useState("");
    const [description, setDescription] = useState("");
    const [image, setImage] = useState<File | null>(null);
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
        const file = e.target.files?.[0];
        if (file) {
            setImage(file);
        }
    }

    const uploadProduct = async () => {
        const token = localStorage.getItem("token");
        try {
            const response = await axiosInstance.post(`/products/add`, {
                name: name,
                price: price,
                description: description,
                image: image,
            }, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "multipart/form-data",
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
     <CustomFormGroup
                label="Product Name"
                type="text"
                value={name}
                handleChange={handleNameChange}
                placeholder="Name"
                controlId="formBasicProductName"
            />
            <CustomFormGroup
                label="Product Price"
                type="text"
                value={price}
                handleChange={handlePriceChange}
                placeholder="Price"
                controlId="formBasicPrice"
            />
            <CustomFormGroup
                label="Product Description"
                type="text"
                value={description}
                handleChange={handleDescriptionChange}
                placeholder="Description"
                controlId="formBasicDescription"
            />
            <CustomFormGroup
                label="Product Image"
                type="file"
                handleChange={handleImageChange}
                controlId="formBasicImage"
            />
        <Button variant="primary" type="submit">
          Upload Product
        </Button>
      </Form>
    </>);
}
 
export default AddProduct;