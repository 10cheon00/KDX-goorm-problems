import { useSelector } from "react-redux";
import CartEmpty from "../../components/cart-empty/CartEmpty";
import CartList from "./cart-list/CartList";

const CartPage = () => {
  const products = useSelector((state) => state.cartReducer.products);

  return (
    <div className="page">
      <div className="container">
        {products.length > 0 ? <CartList /> : <CartEmpty title={"장바구니"} />}
      </div>
    </div>
  );
};

export default CartPage;
