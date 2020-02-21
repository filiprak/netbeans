/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2010 Oracle and/or its affiliates. All rights reserved.
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
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2010 Sun Microsystems, Inc.
 */

/*
 * RevertChangesPanel.java
 *
 * Created on Nov 10, 2010, 3:15:36 PM
 */

package org.netbeans.modules.git.remote.ui.checkout;

/**
 *
 */
public class RevertChangesPanel extends javax.swing.JPanel {

    /** Creates new form RevertChangesPanel */
    public RevertChangesPanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        revertbButtonGroup = new javax.swing.ButtonGroup();
        workingTreeGroup = new javax.swing.ButtonGroup();

        org.openide.awt.Mnemonics.setLocalizedText(removeAllNewCheckBox, org.openide.util.NbBundle.getMessage(RevertChangesPanel.class, "RevertChangesPanel.removeAllNewCheckBox.text")); // NOI18N

        revertbButtonGroup.add(revertWTRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(revertWTRadioButton, org.openide.util.NbBundle.getMessage(RevertChangesPanel.class, "RevertChangesPanel.revertWTRadioButton.text")); // NOI18N
        revertWTRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revertWTRadioButtonActionPerformed(evt);
            }
        });

        revertbButtonGroup.add(revertIndexRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(revertIndexRadioButton, org.openide.util.NbBundle.getMessage(RevertChangesPanel.class, "RevertChangesPanel.revertIndexRadioButton.text")); // NOI18N
        revertIndexRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revertIndexRadioButtonActionPerformed(evt);
            }
        });

        revertbButtonGroup.add(revertAllRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(revertAllRadioButton, org.openide.util.NbBundle.getMessage(RevertChangesPanel.class, "RevertChangesPanel.revertAllRadioButton.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(removeWTNewCheckBox, org.openide.util.NbBundle.getMessage(RevertChangesPanel.class, "RevertChangesPanel.removeWTNewCheckBox.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(revertAllRadioButton)
                    .addComponent(revertWTRadioButton)
                    .addComponent(revertIndexRadioButton)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(removeWTNewCheckBox)
                            .addComponent(removeAllNewCheckBox))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(revertAllRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeAllNewCheckBox)
                .addGap(18, 18, 18)
                .addComponent(revertWTRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeWTNewCheckBox)
                .addGap(18, 18, 18)
                .addComponent(revertIndexRadioButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void revertIndexRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revertIndexRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_revertIndexRadioButtonActionPerformed

    private void revertWTRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revertWTRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_revertWTRadioButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    final javax.swing.JCheckBox removeAllNewCheckBox = new javax.swing.JCheckBox();
    final javax.swing.JCheckBox removeWTNewCheckBox = new javax.swing.JCheckBox();
    final javax.swing.JRadioButton revertAllRadioButton = new javax.swing.JRadioButton();
    final javax.swing.JRadioButton revertIndexRadioButton = new javax.swing.JRadioButton();
    final javax.swing.JRadioButton revertWTRadioButton = new javax.swing.JRadioButton();
    private javax.swing.ButtonGroup revertbButtonGroup;
    private javax.swing.ButtonGroup workingTreeGroup;
    // End of variables declaration//GEN-END:variables

}
