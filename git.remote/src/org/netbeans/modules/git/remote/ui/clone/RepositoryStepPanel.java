/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2009 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */

package org.netbeans.modules.git.remote.ui.clone;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import org.netbeans.modules.git.remote.GitModuleConfig;
import org.netbeans.modules.remotefs.versioning.api.VCSFileProxySupport;
import org.netbeans.modules.versioning.core.api.VCSFileProxy;
import org.openide.filesystems.FileSystem;
import org.openide.util.NbBundle;

/**
 *
 */
public class RepositoryStepPanel extends javax.swing.JPanel {
    private final FileSystem fileSystem;

    /** Creates new form RepositoryPanel */
    public RepositoryStepPanel(FileSystem fs, JPanel repositoryPanel) {
        this.fileSystem = fs;
        initComponents();
        txtDestination.setText(defaultWorkingDirectory().getPath());
        jPanel1.add(repositoryPanel);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        setPreferredSize(getPreferredSize());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMinimumSize(new java.awt.Dimension(480, 160));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/netbeans/modules/git/remote/ui/clone/Bundle"); // NOI18N
        setName(bundle.getString("BK2018")); // NOI18N
        setVerifyInputWhenFocusTarget(false);

        progressPanel.setLayout(null);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(RepositoryStepPanel.class, "LBL_RepositoryStepPanel.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(RepositoryStepPanel.class, "RepositoryStepPanel.jLabel1.text")); // NOI18N

        lblDestination.setLabelFor(txtDestination);
        org.openide.awt.Mnemonics.setLocalizedText(lblDestination, org.openide.util.NbBundle.getMessage(RepositoryStepPanel.class, "RepositoryStepPanel.lblDestination.text")); // NOI18N
        lblDestination.setToolTipText(org.openide.util.NbBundle.getMessage(RepositoryStepPanel.class, "RepositoryStepPanel.lblDestination.TTtext")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnBrowseDestination, org.openide.util.NbBundle.getMessage(RepositoryStepPanel.class, "RepositoryStepPanel.btnBrowse.text")); // NOI18N
        btnBrowseDestination.setToolTipText(org.openide.util.NbBundle.getMessage(RepositoryStepPanel.class, "RepositoryStepPanel.btnBrowse.TTtext")); // NOI18N
        btnBrowseDestination.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseDestinationActionPerformed(evt);
            }
        });

        jLabel3.setForeground(javax.swing.UIManager.getDefaults().getColor("Label.disabledForeground"));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(RepositoryStepPanel.class, "RepositoryStepPanel.destinationHint.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(progressPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblDestination)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtDestination)
                        .addGap(0, 0, 0)
                        .addComponent(lblCloneName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBrowseDestination))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDestination)
                    .addComponent(txtDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowseDestination)
                    .addComponent(lblCloneName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(progressPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(RepositoryStepPanel.class, "ACSD_RepositoryPanel")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    private void btnBrowseDestinationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseDestinationActionPerformed
        VCSFileProxy oldFile = defaultWorkingDirectory();
        JFileChooser fileChooser = VCSFileProxySupport.createFileChooser(oldFile);
        fileChooser.setDialogTitle(NbBundle.getMessage(CloneDestinationPanel.class, "Browse_title"));                                            // NO I18N
        fileChooser.setMultiSelectionEnabled(false);
        FileFilter[] old = fileChooser.getChoosableFileFilters();
        for (FileFilter fileFilter : old) {
            fileChooser.removeChoosableFileFilter(fileFilter);
        }
        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept (File f) {
                return f.isDirectory();
            }
            @Override
            public String getDescription() {
                return NbBundle.getMessage(CloneDestinationPanel.class, "Folders"); // NOI18N
            }
        });
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showDialog(this, NbBundle.getMessage(CloneDestinationPanel.class, "OK_Button"));                                            // NO I18N
        VCSFileProxy f = VCSFileProxySupport.getSelectedFile(fileChooser);
        if (f != null) {
            txtDestination.setText(f.getPath());
        }
    }//GEN-LAST:event_btnBrowseDestinationActionPerformed
    
    /**
     * Returns file to be initally used.
     * <ul>
     * <li>first is takes text in workTextField
     * <li>then recent project folder
     * <li>finally <tt>user.home</tt>
     * <ul>
     */
    private VCSFileProxy defaultWorkingDirectory() {
        VCSFileProxy defaultDir = null;
        String current = txtDestination.getText();
        if (current != null && !(current.trim().equals(""))) {  // NOI18N
            VCSFileProxy currentFile = VCSFileProxySupport.getResource(fileSystem, current);
            while (currentFile != null && currentFile.exists() == false) {
                currentFile = currentFile.getParentFile();
            }
            if (currentFile != null) {
                if (currentFile.isFile()) {
                    defaultDir = currentFile.getParentFile();
                } else {
                    defaultDir = currentFile;
                }
            }
        }
        
        if (defaultDir == null) {
            String cloneDir = GitModuleConfig.getDefault().getPreferences().get(CloneDestinationStep.CLONE_TARGET_DIRECTORY, null);
            if (cloneDir != null) {
                defaultDir = VCSFileProxySupport.getResource(fileSystem, cloneDir);               
            }            
        }

        //if (defaultDir == null) {
        //    VCSFileProxy projectFolder = ProjectChooser.getProjectsFolder();
        //    if (projectFolder.exists() && projectFolder.isDirectory()) {
        //        defaultDir = projectFolder;
        //    }
        //}

        if (defaultDir == null) {
            defaultDir = VCSFileProxySupport.getHome(VCSFileProxy.createFileProxy(fileSystem.getRoot()));
        }

        return defaultDir;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    final javax.swing.JButton btnBrowseDestination = new javax.swing.JButton();
    private final javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
    private final javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
    private final javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
    private final javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
    private final javax.swing.JSeparator jSeparator1 = new javax.swing.JSeparator();
    final javax.swing.JLabel lblCloneName = new javax.swing.JLabel();
    private final javax.swing.JLabel lblDestination = new javax.swing.JLabel();
    final javax.swing.JPanel progressPanel = new javax.swing.JPanel();
    final javax.swing.JTextField txtDestination = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables
        
}
