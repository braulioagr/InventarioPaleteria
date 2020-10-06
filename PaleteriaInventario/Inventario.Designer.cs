namespace PaleteriaInventario
{
    partial class Inventario
    {
        /// <summary>
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de Windows Forms

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Inventario));
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabPageClientes = new System.Windows.Forms.TabPage();
            this.toolStripClientes = new System.Windows.Forms.ToolStrip();
            this.toolStripButton1 = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripButton2 = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripButton3 = new System.Windows.Forms.ToolStripButton();
            this.label1 = new System.Windows.Forms.Label();
            this.textBoxNombreCliente = new System.Windows.Forms.TextBox();
            this.dataGridViewCliente = new System.Windows.Forms.DataGridView();
            this.tabControl1.SuspendLayout();
            this.tabPageClientes.SuspendLayout();
            this.toolStripClientes.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewCliente)).BeginInit();
            this.SuspendLayout();
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.tabPageClientes);
            this.tabControl1.Location = new System.Drawing.Point(12, 12);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(776, 426);
            this.tabControl1.TabIndex = 0;
            // 
            // tabPageClientes
            // 
            this.tabPageClientes.Controls.Add(this.toolStripClientes);
            this.tabPageClientes.Controls.Add(this.label1);
            this.tabPageClientes.Controls.Add(this.textBoxNombreCliente);
            this.tabPageClientes.Controls.Add(this.dataGridViewCliente);
            this.tabPageClientes.Location = new System.Drawing.Point(4, 22);
            this.tabPageClientes.Name = "tabPageClientes";
            this.tabPageClientes.Padding = new System.Windows.Forms.Padding(3);
            this.tabPageClientes.Size = new System.Drawing.Size(768, 400);
            this.tabPageClientes.TabIndex = 0;
            this.tabPageClientes.Text = "Clientes";
            this.tabPageClientes.UseVisualStyleBackColor = true;
            // 
            // toolStripClientes
            // 
            this.toolStripClientes.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripButton1,
            this.toolStripSeparator1,
            this.toolStripButton2,
            this.toolStripSeparator2,
            this.toolStripButton3});
            this.toolStripClientes.Location = new System.Drawing.Point(3, 3);
            this.toolStripClientes.Name = "toolStripClientes";
            this.toolStripClientes.Size = new System.Drawing.Size(762, 37);
            this.toolStripClientes.TabIndex = 3;
            this.toolStripClientes.Text = "toolStrip1";
            this.toolStripClientes.ItemClicked += new System.Windows.Forms.ToolStripItemClickedEventHandler(this.toolStripClientes_ItemClicked);
            // 
            // toolStripButton1
            // 
            this.toolStripButton1.AccessibleName = "Agregar";
            this.toolStripButton1.AutoToolTip = false;
            this.toolStripButton1.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.toolStripButton1.Image = ((System.Drawing.Image)(resources.GetObject("toolStripButton1.Image")));
            this.toolStripButton1.ImageScaling = System.Windows.Forms.ToolStripItemImageScaling.None;
            this.toolStripButton1.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.toolStripButton1.Name = "toolStripButton1";
            this.toolStripButton1.Size = new System.Drawing.Size(34, 34);
            this.toolStripButton1.Text = "Agregar un cliente";
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(6, 37);
            // 
            // toolStripButton2
            // 
            this.toolStripButton2.AccessibleName = "actualizar";
            this.toolStripButton2.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.toolStripButton2.Image = ((System.Drawing.Image)(resources.GetObject("toolStripButton2.Image")));
            this.toolStripButton2.ImageScaling = System.Windows.Forms.ToolStripItemImageScaling.None;
            this.toolStripButton2.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.toolStripButton2.Name = "toolStripButton2";
            this.toolStripButton2.Size = new System.Drawing.Size(34, 34);
            this.toolStripButton2.Text = "toolStripButton2";
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(6, 37);
            // 
            // toolStripButton3
            // 
            this.toolStripButton3.AccessibleName = "Eliminar";
            this.toolStripButton3.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.toolStripButton3.Image = ((System.Drawing.Image)(resources.GetObject("toolStripButton3.Image")));
            this.toolStripButton3.ImageScaling = System.Windows.Forms.ToolStripItemImageScaling.None;
            this.toolStripButton3.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.toolStripButton3.Name = "toolStripButton3";
            this.toolStripButton3.Size = new System.Drawing.Size(34, 34);
            this.toolStripButton3.Text = "toolStripButton3";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(595, 46);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(54, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "busqueda";
            // 
            // textBoxNombreCliente
            // 
            this.textBoxNombreCliente.AccessibleName = "Cliente";
            this.textBoxNombreCliente.Location = new System.Drawing.Point(598, 62);
            this.textBoxNombreCliente.Name = "textBoxNombreCliente";
            this.textBoxNombreCliente.Size = new System.Drawing.Size(164, 20);
            this.textBoxNombreCliente.TabIndex = 1;
            this.textBoxNombreCliente.KeyUp += new System.Windows.Forms.KeyEventHandler(this.busqueda_keyUp);
            // 
            // dataGridViewCliente
            // 
            this.dataGridViewCliente.AllowUserToAddRows = false;
            this.dataGridViewCliente.AllowUserToDeleteRows = false;
            this.dataGridViewCliente.AllowUserToOrderColumns = true;
            this.dataGridViewCliente.AllowUserToResizeRows = false;
            this.dataGridViewCliente.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewCliente.Location = new System.Drawing.Point(6, 88);
            this.dataGridViewCliente.Name = "dataGridViewCliente";
            this.dataGridViewCliente.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dataGridViewCliente.Size = new System.Drawing.Size(756, 306);
            this.dataGridViewCliente.TabIndex = 0;
            this.dataGridViewCliente.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridViewCliente_CellClick);
            // 
            // Inventario
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.tabControl1);
            this.Name = "Inventario";
            this.Text = "Paleteria";
            this.Load += new System.EventHandler(this.Inventario_Load);
            this.Resize += new System.EventHandler(this.Inventario_Resize);
            this.tabControl1.ResumeLayout(false);
            this.tabPageClientes.ResumeLayout(false);
            this.tabPageClientes.PerformLayout();
            this.toolStripClientes.ResumeLayout(false);
            this.toolStripClientes.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewCliente)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabPageClientes;
        private System.Windows.Forms.DataGridView dataGridViewCliente;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox textBoxNombreCliente;
        private System.Windows.Forms.ToolStrip toolStripClientes;
        private System.Windows.Forms.ToolStripButton toolStripButton1;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripButton toolStripButton2;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripButton toolStripButton3;
    }
}

