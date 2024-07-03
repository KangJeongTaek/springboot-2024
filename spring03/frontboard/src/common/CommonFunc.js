export function formatDate(date){
    let result = date.replace('T',' ');
    let index = result.lastIndexOf(':'); // 초 앞에 있는 위치값
    result = result.substr(0,index);

    return result;


    //2024-06-28T10:30:00.148995
}