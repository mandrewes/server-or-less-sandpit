import React from 'react'
import { BrowserRouter, Switch, Route } from 'react-router-dom'

import SearchDocumentsPage from './pages/SearchDocumentsPage';
import AddDocumentPage from './pages/AddDocumentPage';
import NotFoundPage from './pages/NotFoundPage';


export default (
    <BrowserRouter>
      <Switch>
           <Route exact path='/search' component={SearchDocumentsPage}/>
           <Route exact path='/add' component={AddDocumentPage}/>
           <Route path="*" component={NotFoundPage}/>
         </Switch>
   </BrowserRouter>
);