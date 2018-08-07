import Config from '../config';
import axios from 'axios';
import { toast } from 'react-toastify';

class Api {

  get(uri,_cb,_err) {
        var url = Config.api + uri;
        axios.get( url, {headers: {}})
            .then(function(response) {
                _cb(response)
            }).catch(function (error) {
                console.log(error);
                if ( _err ) {
                    _err(error);
                    return;
                }
                toast.error(r => "Error " + error )
            });
  }

  post(uri,body,_cb,_err) {
        var url = Config.api + uri;
        console.log("Config.api is " + Config.api)
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
                console.log(error);
                if ( error.response && error.response.data && error.response.data.message ) {
                    toast.warn(r => error.response.data.message );
                } else {
                    toast.error(r => "Error " + error );
                }
            });
  }
}

var instance = new Api();

export default instance;