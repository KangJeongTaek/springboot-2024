import React from 'react';
const IncButton = ({count,onClick}) => {
// const [count,setCount] = useState(0); // count는 변수, setCount는 변수값을 조정할 함수, useState(0)은 초기값은 0이라는 뜻

// function upClick(){
//     setCount(count +1);
//     }

return (
    <button onClick={onClick} count={count}>
    {count}번 증가!
    </button>
);  
};

export default IncButton;