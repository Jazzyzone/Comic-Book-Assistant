import axios from 'axios';


export default {
    getComicList(config){
        return axios.get("/marvel/",null,config);
    },
    getComicListTest(config){
        config = {
            title : "Uncanny X-Men",
            issue : 1,
            page : 1
        }
        return axios.get("/marvel/",null,config);
    }
}