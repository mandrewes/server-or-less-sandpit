import React from 'react'
import { BrowserRouter, Switch, Route } from 'react-router-dom'

import SearchDocumentsPage from './pages/SearchDocumentsPage';

export default (
    <BrowserRouter>
      <Switch>
           <Route exact path='/search' component={SearchDocumentsPage}/>
           <Route path="*" component={NotFoundPage}/>

         </Switch>
   </BrowserRouter>
);