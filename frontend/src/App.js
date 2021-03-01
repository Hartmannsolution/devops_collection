import React from 'react';
import { Routes, Route } from 'react-router-dom';
import NoMatch from './components/NoMatch';
import Content3 from './components/Content3';
import Home from './components/Home';
import Header from './components/Header';
import ParentNested from './components/nested/ParentNested';
import ChildNested from './components/nested/ChildNested';
import DisplayParams from './components/nested/ChildNested2';

function App() {
  return (
    <div>
      <Header />
      <hr />
      <div className='content'>
        <Routes>
          <Route path='/' element={<Home />} />

          <Route path='Content3' element={<Content3 />} />

          <Route path='parentNested' element={<ParentNested />}>
            <Route path='childNested' element={<ChildNested />} />
            <Route path=':displayParam' element={<DisplayParams />} />
          </Route>

          <Route path='*' element={<NoMatch />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
