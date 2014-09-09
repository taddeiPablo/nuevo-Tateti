/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.app.TaTeTi;


import android.os.Bundle;
import org.apache.cordova.*;
import controladores.TatetiController;

/**
 * ACTIVITY DEL LAYOUT INDEX.HTML
 * @author pablo taddei
 *
 */
public class TATETI extends CordovaActivity 
{
	private TatetiController controller = null;
	
	/**
	 * aqui se lanza la ventana principal
	 */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.init();
        //TODO: aqui se crea el controlador para los eventos de la ventana de inicio
        this.controller = new TatetiController(this);
        //TODO :aqui se determinar la interface que va a interacturar con javascript
        this.appView.addJavascriptInterface(this.controller, "TatetiController");
        //TODO : Set by <content src="index.html" /> in config.xml
        super.loadUrl(Config.getStartUrl());
        //super.loadUrl("file:///android_asset/www/index.html");
    }
}

