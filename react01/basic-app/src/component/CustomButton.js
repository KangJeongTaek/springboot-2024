import React from 'react';





const CustomButton = ({data}) => {
    let heroName = data.heroName;
    let isLoggedIn = false; // 로그인 됐다는 뜻

    function handleClick(name){
        if(isLoggedIn){
            alert(name + " You've logged out");
            
        }else{
            alert(name + " You've to log in!");
            
        }
    }
    return (
        <>
            {isLoggedIn?(<button onClick= { () =>handleClick(heroName)}>Log out</button>):(<button onClick={ () => handleClick(heroName)}>Log in</button>)}
        </>
    );
};

export default CustomButton;