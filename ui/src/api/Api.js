//import React, { Component } from 'react';
import Config from '../config';
import Logger from '../services/Logger'
import axios from 'axios';
import { toast } from 'react-toastify';

class Api {

  get(uri,_cb,_err) {
        var url = Config.api + uri;
        axios.get( url, {headers: {}})
            .then(function(response) {
                _cb(response)
            }).catch(function (error) {
                Logger.error(error);
                if ( _err ) {
                    _err(error);
                    return;
                }
                toast.error(r => "Error " + error )
            });
  }

  post(uri,body,_cb,_err) {
        var url = Config.api + uri;
        axios.post(
            url,
            body,
            {
                headers: {
                            'Content-Type': 'application/json',
                        }
            })
            .then(function(response) {
                _cb(response)
            }).catch(function (error) {
                if ( _err ) {
                    _err(error);
                }
              Logger.error(error);
              toast.error(r => "Error " + error )
            });
  }
}

var instance = new Api();

export default instance;