import { useDispatch, useSelector } from "react-redux";
import styles from "./Nav.module.scss";
import { BsCart2 } from "react-icons/bs";
import { FaUser } from "react-icons/fa";
import { MdLogin, MdLogout } from "react-icons/md";
import { Link } from "react-router-dom";
import { remove } from "../../../store/user/user.slice";
import NavCartBlock from "./nav-cart-block/NavCartBlock";

const Nav = () => {
  const userId = useSelector((state) => state.userReducer.id);
  const products = useSelector((state) => state.cartReducer.products);
  const dispatch = useDispatch();
  const logout = () => {
    dispatch(remove());
  };

  return (
    <nav className={styles.nav}>
      <ul>
        <li>
          <Link to="/cart">
            <BsCart2 />
            {products.length > 0 && (
              <span className={styles.counter}>
                <b>{products.length}</b>
              </span>
            )}
          </Link>

          {products.length > 0 && (
            <div className={styles.nav_hover_cart}>
              <NavCartBlock />
            </div>
          )}
        </li>
        <li>
          <FaUser />
        </li>
        <li className={styles.nav_sign_out}>
          {userId === "" ? (
            <Link to="/login">
              <MdLogin />
            </Link>
          ) : (
            <Link to="/" onClick={logout}>
              <MdLogout />
            </Link>
          )}
        </li>
      </ul>
    </nav>
  );
};

export default Nav;
