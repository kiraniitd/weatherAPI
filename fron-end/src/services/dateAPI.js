import axios from 'axios';
const DATE_JSON_URL = 'https://jsonmock.hackerrank.com/datetime';

export default {
    getAPIResponse() {        
        return axios.get(DATE_JSON_URL).then(response => {            
            return response.data})
            .catch(error => {
                return null;
        });
    }
};
