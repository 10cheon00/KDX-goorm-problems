import { FilterType } from "../../../store/filter/filterSlice";

interface FilterRadioButtonProps {
  handleFilter: (e: React.ChangeEvent<HTMLInputElement>) => void;
  selectedType: typeof FilterType[keyof typeof FilterType],
  Type: typeof FilterType[keyof typeof FilterType],
  label: string
}

const FilterRadioButton = ({handleFilter, selectedType, Type, label}: FilterRadioButtonProps) => {
  return (
    <div className="filters__check">
      <input
        type="radio"
        name="filter"
        value={Type}
        id={Type}
        checked={selectedType === Type}
        onChange={(e) => handleFilter(e)}
      />
      <label htmlFor={Type}>{label}</label>
    </div>
  );
};

export default FilterRadioButton;