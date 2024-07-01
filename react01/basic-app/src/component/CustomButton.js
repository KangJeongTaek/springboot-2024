import React from 'react';





const CustomButton = () => {
    let isLoggedIn = true; // 로그인 됐다는 뜻
    return (
        <>
            {isLoggedIn?(<button>Log out</button>):(<button>Log in</button>)}
        </>
    );
};

export default CustomButton;