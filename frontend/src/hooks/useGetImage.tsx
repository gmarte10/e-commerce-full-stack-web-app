import axiosInstance from "../components/api/axiosInstance";

const useGetImage = () => {
  const getImage = async (image: string) => {
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
      return null;
    }
  };
  return getImage;
};

export default useGetImage;
