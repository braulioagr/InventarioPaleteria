namespace PaleteriaInventario
{
    partial class SeleccionaSucursal
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.dataGridViewSucursal = new System.Windows.Forms.DataGridView();
            this.button1 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewSucursal)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridViewSucursal
            // 
            this.dataGridViewSucursal.AllowUserToAddRows = false;
            this.dataGridViewSucursal.AllowUserToDeleteRows = false;
            this.dataGridViewSucursal.AllowUserToResizeRows = false;
            this.dataGridViewSucursal.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewSucursal.Location = new System.Drawing.Point(13, 13);
            this.dataGridViewSucursal.Name = "dataGridViewSucursal";
            this.dataGridViewSucursal.ReadOnly = true;
            this.dataGridViewSucursal.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dataGridViewSucursal.Size = new System.Drawing.Size(541, 192);
            this.dataGridViewSucursal.TabIndex = 0;
            this.dataGridViewSucursal.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridViewSucursal_CellClick);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(479, 211);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 1;
            this.button1.Text = "Aceptar";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // button2
            // 
            this.button2.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.button2.Location = new System.Drawing.Point(12, 211);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(75, 23);
            this.button2.TabIndex = 2;
            this.button2.Text = "Cancelar";
            this.button2.UseVisualStyleBackColor = true;
            // 
            // SeleccionaSucursal
            // 
            this.AcceptButton = this.button1;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.CancelButton = this.button2;
            this.ClientSize = new System.Drawing.Size(566, 246);
            this.ControlBox = false;
            this.Controls.Add(this.button2);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.dataGridViewSucursal);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "SeleccionaSucursal";
            this.Text = "Selecciona Sucursal";
            this.Load += new System.EventHandler(this.SeleccionaSucursal_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewSucursal)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridViewSucursal;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button button2;
    }
}