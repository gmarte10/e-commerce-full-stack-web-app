import { Form } from "react-bootstrap";

interface CustomFormGroupProps {
  label: string;
  type: string;
  value?: string | number;
  handleChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  placeholder?: string;
  controlId: string;
}
const CustomFormGroup: React.FC<CustomFormGroupProps> = ({
  label,
  type,
  value,
  handleChange,
  placeholder,
  controlId,
}) => {
  return (
    <>
      <Form.Group className="mb-3" controlId={controlId}>
        <Form.Label>{label}</Form.Label>
        <Form.Control
          type={type}
          placeholder={placeholder}
          value={type === "file" ? undefined : value}
          onChange={handleChange}
        />
      </Form.Group>
    </>
  );
};

export default CustomFormGroup;
