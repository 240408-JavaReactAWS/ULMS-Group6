// check for localstorage item 'userId' and 'role' and redirect to dashboard if user is already logged in
import { useNavigate } from 'react-router-dom';
import { useEffect } from 'react';

export default function Home() {
    const navigate = useNavigate();
    useEffect(() => {
        if(localStorage.getItem('userId')){
          if(localStorage.getItem('role') === 'ADMIN'){
            navigate('/adminDashboard');
          } else {
            navigate('/dashboard');
          }
        } else {
          navigate('/login');
        }
      }, [navigate]);

    return null;
}
