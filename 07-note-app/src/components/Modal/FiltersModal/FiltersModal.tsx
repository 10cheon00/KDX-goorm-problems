import React from 'react'
import { FaTimes } from 'react-icons/fa';
import { useAppDispatch } from '../../../hooks/redux';
import { toggleFiltersModal } from '../../../store/modal/modalSlice';
import { FilterType } from '../../../store/filter/filterSlice';
import { DeleteBox, FixedContainer } from '../Modal.styles';
import { Box, Container, TopBox } from './FilterModal.styles';
import FilterRadioButton from './FilterRadioButton';

interface FiltersModalProps {
  handleFilter: (e: React.ChangeEvent<HTMLInputElement>) => void;
  handleClear: () => void;
  filterType: typeof FilterType[keyof typeof FilterType]
}

const FiltersModal = ({ handleFilter, handleClear, filterType }: FiltersModalProps) => {

  const dispatch = useAppDispatch();

  return (
    <FixedContainer>
      <Container>
        <DeleteBox
          onClick={() => dispatch(toggleFiltersModal(false))}
          className="filters__close"
        >
          <FaTimes />
        </DeleteBox>
        <TopBox>
          <div className='filters__title'>정렬</div>
          <small onClick={handleClear} className="filters__delete">
            CLEAR
          </small>
        </TopBox>

        <Box>
          <div className='filters__subtitle'>PRIORITY</div>
          <FilterRadioButton 
            handleFilter={handleFilter} 
            selectedType={filterType} 
            Type={FilterType.PRIORITY_LOW} 
            label={"Low to High"}          
          />
          <FilterRadioButton 
            handleFilter={handleFilter} 
            selectedType={filterType} 
            Type={FilterType.PRIORITY_HIGH} 
            label={"High to Low"}          
          />
        </Box>

        <Box>
          <div className='filters__subtitle'>DATE</div>
          <FilterRadioButton 
            handleFilter={handleFilter} 
            selectedType={filterType} 
            Type={FilterType.LATEST} 
            label={"Sort by Latest"}          
          />
         <FilterRadioButton 
            handleFilter={handleFilter} 
            selectedType={filterType} 
            Type={FilterType.CREATED} 
            label={"Sort by Created"}          
          />
          <FilterRadioButton 
            handleFilter={handleFilter} 
            selectedType={filterType} 
            Type={FilterType.EDITED} 
            label={"Sort by Edited"}          
          />
          {/* <div className='filters__check'>
            <input
              type="radio"
              name="filter"
              value={FilterType.EDITED}
              id={FilterType.EDITED}
              checked={filterType === FilterType.EDITED}
              onChange={(e) => handleFilter(e)}
            />
            <label htmlFor='edit'>Sort by Edited</label>
          </div> */}
        </Box>


      </Container>
    </FixedContainer>
  )
}

export default FiltersModal