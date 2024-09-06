import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";

import Layout from './components/layout/Layout';
import HomePage from './pages/HomePage';
import DetailPage from './pages/DetailPage';
import NotFoundPage from './pages/NotFoundPage';
import CartPage from './pages/CartPage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';

const router = createBrowserRouter([
  {
    path: "/",
    element: <HomePage />,
  },
  {
    path: "/404",
    element: <NotFoundPage />
  }
]);

function App() {
  return (
    <Layout>
      <RouterProvider router={router} />
    </Layout>
  )
}

export default App
