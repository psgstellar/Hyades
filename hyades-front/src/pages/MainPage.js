import Header from "../components/common/Header";
import CrewCard from "../components/CrewCard"
import req from "../lib/api/projectAPI";
import { useQuery } from "react-query";
const MainPage = () => {
  // 추후 컨테이너 컴포넌트들로 변경
  const user = {
    username: "tester",
  };

  const { isLoading, error, data } = useQuery("data", () => {
    return req.get("/3");
  });

  const data1 = {
    data: {
      header: {
        name:"name"
      },
      section: {  
        img:`${process.env.PUBLIC_URL}/assets/images/profile_image.webp`,
        introduce:"introduce",
      },
      body:{
        detailIntroduce:"detailIntroduce"
      }
    }
  };

  return (
    <>
      <Header />
      
      <CrewCard data={data} isLoading={isLoading} error={error}/> 
    </>
  );
};

export default MainPage;
