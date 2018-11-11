/*
 * File    : PluginStringParameter.java
 * Created : 15 d�c. 2003}
 * By      : Olivier
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details ( see the LICENSE file ).
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.biglybt.ui.swt.config.plugins;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;

import com.biglybt.core.internat.MessageText;
import com.biglybt.ui.swt.Messages;
import com.biglybt.ui.swt.Utils;
import com.biglybt.ui.swt.imageloader.ImageLoader;

import com.biglybt.pifimpl.local.ui.config.FileParameter;

/**
 * @author Olivier
 *
 */
public class PluginFileParameter implements PluginParameterImpl {

  Control[] controls;

  public PluginFileParameter(final Composite pluginGroup,FileParameter parameter) {
    controls = new Control[3];

    controls[0] = new Label(pluginGroup,SWT.NULL);
    Messages.setLanguageText(controls[0],parameter.getLabelKey());

    final com.biglybt.ui.swt.config.StringParameter sp =
    	new com.biglybt.ui.swt.config.StringParameter(
    	    pluginGroup,
    	    parameter.getKey(),
					parameter.getDefaultValue());
    controls[1] = sp.getControl();
    GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
    controls[1].setLayoutData(gridData);

    Button browse = new Button(pluginGroup, SWT.PUSH);
    ImageLoader.getInstance().setButtonImage(browse, "openFolderButton");
    Utils.setTT(browse,MessageText.getString("ConfigView.button.browse"));

    browse.addListener(SWT.Selection, new Listener() {
      @Override
      public void handleEvent(Event event) {
        FileDialog dialog = new FileDialog(pluginGroup.getShell(), SWT.APPLICATION_MODAL);
        dialog.setFilterPath(sp.getValue());
        String path = dialog.open();
        if (path != null) {
          sp.setValue(path);
        }
      }
    });

    controls[2] = browse;
  }

  @Override
  public Control[] getControls(){
    return controls;
  }

}
