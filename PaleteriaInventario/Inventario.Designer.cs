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
            this.tabControl = new System.Windows.Forms.TabControl();
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
            this.tabPageCategoria = new System.Windows.Forms.TabPage();
            this.toolStripCategorias = new System.Windows.Forms.ToolStrip();
            this.toolStripButton4 = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripButton5 = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator4 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripButton6 = new System.Windows.Forms.ToolStripButton();
            this.label2 = new System.Windows.Forms.Label();
            this.textBoxCategoria = new System.Windows.Forms.TextBox();
            this.dataGridViewCategoria = new System.Windows.Forms.DataGridView();
            this.tabPageStock = new System.Windows.Forms.TabPage();
            this.textBoxProductosStock = new System.Windows.Forms.TextBox();
            this.textBoxSucursalesStock = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.dataGridViewStockProductos = new System.Windows.Forms.DataGridView();
            this.dataGridViewStock = new System.Windows.Forms.DataGridView();
            this.dataGridViewStockSucursales = new System.Windows.Forms.DataGridView();
            this.tabControl.SuspendLayout();
            this.tabPageClientes.SuspendLayout();
            this.toolStripClientes.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewCliente)).BeginInit();
            this.tabPageCategoria.SuspendLayout();
            this.toolStripCategorias.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewCategoria)).BeginInit();
            this.tabPageStock.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewStockProductos)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewStock)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewStockSucursales)).BeginInit();
            this.SuspendLayout();
            // 
            // tabControl
            // 
            this.tabControl.Controls.Add(this.tabPageClientes);
            this.tabControl.Controls.Add(this.tabPageCategoria);
            this.tabControl.Controls.Add(this.tabPageStock);
            this.tabControl.Location = new System.Drawing.Point(12, 12);
            this.tabControl.Name = "tabControl";
            this.tabControl.SelectedIndex = 0;
            this.tabControl.Size = new System.Drawing.Size(776, 426);
            this.tabControl.TabIndex = 0;
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
            this.toolStripButton2.AccessibleName = "Actualizar";
            this.toolStripButton2.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.toolStripButton2.Image = ((System.Drawing.Image)(resources.GetObject("toolStripButton2.Image")));
            this.toolStripButton2.ImageScaling = System.Windows.Forms.ToolStripItemImageScaling.None;
            this.toolStripButton2.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.toolStripButton2.Name = "toolStripButton2";
            this.toolStripButton2.Size = new System.Drawing.Size(34, 34);
            this.toolStripButton2.Text = "Actualizar datos de cliente";
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
            this.dataGridViewCliente.ReadOnly = true;
            this.dataGridViewCliente.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dataGridViewCliente.Size = new System.Drawing.Size(756, 306);
            this.dataGridViewCliente.TabIndex = 0;
            this.dataGridViewCliente.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridViewCliente_CellClick);
            // 
            // tabPageCategoria
            // 
            this.tabPageCategoria.Controls.Add(this.toolStripCategorias);
            this.tabPageCategoria.Controls.Add(this.label2);
            this.tabPageCategoria.Controls.Add(this.textBoxCategoria);
            this.tabPageCategoria.Controls.Add(this.dataGridViewCategoria);
            this.tabPageCategoria.Location = new System.Drawing.Point(4, 22);
            this.tabPageCategoria.Name = "tabPageCategoria";
            this.tabPageCategoria.Padding = new System.Windows.Forms.Padding(3);
            this.tabPageCategoria.Size = new System.Drawing.Size(768, 400);
            this.tabPageCategoria.TabIndex = 1;
            this.tabPageCategoria.Text = "Categoria";
            this.tabPageCategoria.UseVisualStyleBackColor = true;
            // 
            // toolStripCategorias
            // 
            this.toolStripCategorias.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripButton4,
            this.toolStripSeparator3,
            this.toolStripButton5,
            this.toolStripSeparator4,
            this.toolStripButton6});
            this.toolStripCategorias.Location = new System.Drawing.Point(3, 3);
            this.toolStripCategorias.Name = "toolStripCategorias";
            this.toolStripCategorias.Size = new System.Drawing.Size(762, 37);
            this.toolStripCategorias.TabIndex = 7;
            this.toolStripCategorias.Text = "toolStrip1";
            this.toolStripCategorias.ItemClicked += new System.Windows.Forms.ToolStripItemClickedEventHandler(this.toolStripCategorias_ItemClicked);
            // 
            // toolStripButton4
            // 
            this.toolStripButton4.AccessibleName = "Agregar";
            this.toolStripButton4.AutoToolTip = false;
            this.toolStripButton4.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.toolStripButton4.Image = ((System.Drawing.Image)(resources.GetObject("toolStripButton4.Image")));
            this.toolStripButton4.ImageScaling = System.Windows.Forms.ToolStripItemImageScaling.None;
            this.toolStripButton4.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.toolStripButton4.Name = "toolStripButton4";
            this.toolStripButton4.Size = new System.Drawing.Size(34, 34);
            this.toolStripButton4.Text = "Agregar un cliente";
            // 
            // toolStripSeparator3
            // 
            this.toolStripSeparator3.Name = "toolStripSeparator3";
            this.toolStripSeparator3.Size = new System.Drawing.Size(6, 37);
            // 
            // toolStripButton5
            // 
            this.toolStripButton5.AccessibleName = "Actualizar";
            this.toolStripButton5.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.toolStripButton5.Image = ((System.Drawing.Image)(resources.GetObject("toolStripButton5.Image")));
            this.toolStripButton5.ImageScaling = System.Windows.Forms.ToolStripItemImageScaling.None;
            this.toolStripButton5.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.toolStripButton5.Name = "toolStripButton5";
            this.toolStripButton5.Size = new System.Drawing.Size(34, 34);
            this.toolStripButton5.Text = "Actualizar datos de cliente";
            // 
            // toolStripSeparator4
            // 
            this.toolStripSeparator4.Name = "toolStripSeparator4";
            this.toolStripSeparator4.Size = new System.Drawing.Size(6, 37);
            // 
            // toolStripButton6
            // 
            this.toolStripButton6.AccessibleName = "Eliminar";
            this.toolStripButton6.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.toolStripButton6.Image = ((System.Drawing.Image)(resources.GetObject("toolStripButton6.Image")));
            this.toolStripButton6.ImageScaling = System.Windows.Forms.ToolStripItemImageScaling.None;
            this.toolStripButton6.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.toolStripButton6.Name = "toolStripButton6";
            this.toolStripButton6.Size = new System.Drawing.Size(34, 34);
            this.toolStripButton6.Text = "toolStripButton3";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(595, 48);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(54, 13);
            this.label2.TabIndex = 6;
            this.label2.Text = "busqueda";
            // 
            // textBoxCategoria
            // 
            this.textBoxCategoria.AccessibleName = "Categoria";
            this.textBoxCategoria.Location = new System.Drawing.Point(598, 64);
            this.textBoxCategoria.Name = "textBoxCategoria";
            this.textBoxCategoria.Size = new System.Drawing.Size(164, 20);
            this.textBoxCategoria.TabIndex = 5;
            this.textBoxCategoria.KeyUp += new System.Windows.Forms.KeyEventHandler(this.busqueda_keyUp);
            // 
            // dataGridViewCategoria
            // 
            this.dataGridViewCategoria.AllowUserToAddRows = false;
            this.dataGridViewCategoria.AllowUserToDeleteRows = false;
            this.dataGridViewCategoria.AllowUserToOrderColumns = true;
            this.dataGridViewCategoria.AllowUserToResizeRows = false;
            this.dataGridViewCategoria.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewCategoria.Location = new System.Drawing.Point(6, 90);
            this.dataGridViewCategoria.Name = "dataGridViewCategoria";
            this.dataGridViewCategoria.ReadOnly = true;
            this.dataGridViewCategoria.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dataGridViewCategoria.Size = new System.Drawing.Size(756, 306);
            this.dataGridViewCategoria.TabIndex = 4;
            this.dataGridViewCategoria.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridViewCategoria_CellClick);
            // 
            // tabPageStock
            // 
            this.tabPageStock.Controls.Add(this.textBoxProductosStock);
            this.tabPageStock.Controls.Add(this.textBoxSucursalesStock);
            this.tabPageStock.Controls.Add(this.label5);
            this.tabPageStock.Controls.Add(this.label4);
            this.tabPageStock.Controls.Add(this.label3);
            this.tabPageStock.Controls.Add(this.dataGridViewStockProductos);
            this.tabPageStock.Controls.Add(this.dataGridViewStock);
            this.tabPageStock.Controls.Add(this.dataGridViewStockSucursales);
            this.tabPageStock.Location = new System.Drawing.Point(4, 22);
            this.tabPageStock.Name = "tabPageStock";
            this.tabPageStock.Padding = new System.Windows.Forms.Padding(3);
            this.tabPageStock.Size = new System.Drawing.Size(768, 400);
            this.tabPageStock.TabIndex = 2;
            this.tabPageStock.Text = "Stock";
            this.tabPageStock.UseVisualStyleBackColor = true;
            // 
            // textBoxProductosStock
            // 
            this.textBoxProductosStock.Location = new System.Drawing.Point(614, 58);
            this.textBoxProductosStock.Name = "textBoxProductosStock";
            this.textBoxProductosStock.Size = new System.Drawing.Size(148, 20);
            this.textBoxProductosStock.TabIndex = 7;
            this.textBoxProductosStock.KeyUp += new System.Windows.Forms.KeyEventHandler(this.textBoxProductosStock_KeyUp);
            // 
            // textBoxSucursalesStock
            // 
            this.textBoxSucursalesStock.Location = new System.Drawing.Point(71, 58);
            this.textBoxSucursalesStock.Name = "textBoxSucursalesStock";
            this.textBoxSucursalesStock.Size = new System.Drawing.Size(144, 20);
            this.textBoxSucursalesStock.TabIndex = 6;
            this.textBoxSucursalesStock.KeyUp += new System.Windows.Forms.KeyEventHandler(this.textBoxSucursalesStock_KeyUp);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(270, 133);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(35, 13);
            this.label5.TabIndex = 5;
            this.label5.Text = "Stock";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(553, 61);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(55, 13);
            this.label4.TabIndex = 4;
            this.label4.Text = "Productos";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(6, 61);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(59, 13);
            this.label3.TabIndex = 3;
            this.label3.Text = "Sucursales";
            // 
            // dataGridViewStockProductos
            // 
            this.dataGridViewStockProductos.AllowUserToAddRows = false;
            this.dataGridViewStockProductos.AllowUserToDeleteRows = false;
            this.dataGridViewStockProductos.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.AllCells;
            this.dataGridViewStockProductos.AutoSizeRowsMode = System.Windows.Forms.DataGridViewAutoSizeRowsMode.AllCells;
            this.dataGridViewStockProductos.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewStockProductos.Location = new System.Drawing.Point(556, 84);
            this.dataGridViewStockProductos.Name = "dataGridViewStockProductos";
            this.dataGridViewStockProductos.ReadOnly = true;
            this.dataGridViewStockProductos.RowHeadersVisible = false;
            this.dataGridViewStockProductos.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dataGridViewStockProductos.Size = new System.Drawing.Size(206, 310);
            this.dataGridViewStockProductos.TabIndex = 2;
            this.dataGridViewStockProductos.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridViewStockProductos_CellClick);
            // 
            // dataGridViewStock
            // 
            this.dataGridViewStock.AllowUserToAddRows = false;
            this.dataGridViewStock.AllowUserToDeleteRows = false;
            this.dataGridViewStock.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.AllCells;
            this.dataGridViewStock.AutoSizeRowsMode = System.Windows.Forms.DataGridViewAutoSizeRowsMode.AllCells;
            this.dataGridViewStock.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewStock.Location = new System.Drawing.Point(273, 149);
            this.dataGridViewStock.Name = "dataGridViewStock";
            this.dataGridViewStock.ReadOnly = true;
            this.dataGridViewStock.RowHeadersVisible = false;
            this.dataGridViewStock.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dataGridViewStock.Size = new System.Drawing.Size(244, 245);
            this.dataGridViewStock.TabIndex = 1;
            // 
            // dataGridViewStockSucursales
            // 
            this.dataGridViewStockSucursales.AllowUserToAddRows = false;
            this.dataGridViewStockSucursales.AllowUserToDeleteRows = false;
            this.dataGridViewStockSucursales.AllowUserToOrderColumns = true;
            this.dataGridViewStockSucursales.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.AllCells;
            this.dataGridViewStockSucursales.AutoSizeRowsMode = System.Windows.Forms.DataGridViewAutoSizeRowsMode.AllCells;
            this.dataGridViewStockSucursales.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewStockSucursales.Location = new System.Drawing.Point(9, 84);
            this.dataGridViewStockSucursales.Name = "dataGridViewStockSucursales";
            this.dataGridViewStockSucursales.ReadOnly = true;
            this.dataGridViewStockSucursales.RowHeadersVisible = false;
            this.dataGridViewStockSucursales.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dataGridViewStockSucursales.Size = new System.Drawing.Size(206, 310);
            this.dataGridViewStockSucursales.TabIndex = 0;
            this.dataGridViewStockSucursales.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridViewStockSucursales_CellClick);
            // 
            // Inventario
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.tabControl);
            this.Name = "Inventario";
            this.Text = "Paleteria";
            this.Load += new System.EventHandler(this.Inventario_Load);
            this.Resize += new System.EventHandler(this.Inventario_Resize);
            this.tabControl.ResumeLayout(false);
            this.tabPageClientes.ResumeLayout(false);
            this.tabPageClientes.PerformLayout();
            this.toolStripClientes.ResumeLayout(false);
            this.toolStripClientes.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewCliente)).EndInit();
            this.tabPageCategoria.ResumeLayout(false);
            this.tabPageCategoria.PerformLayout();
            this.toolStripCategorias.ResumeLayout(false);
            this.toolStripCategorias.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewCategoria)).EndInit();
            this.tabPageStock.ResumeLayout(false);
            this.tabPageStock.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewStockProductos)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewStock)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewStockSucursales)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tabControl;
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
        private System.Windows.Forms.TabPage tabPageCategoria;
        private System.Windows.Forms.ToolStrip toolStripCategorias;
        private System.Windows.Forms.ToolStripButton toolStripButton4;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
        private System.Windows.Forms.ToolStripButton toolStripButton5;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator4;
        private System.Windows.Forms.ToolStripButton toolStripButton6;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox textBoxCategoria;
        private System.Windows.Forms.DataGridView dataGridViewCategoria;
        private System.Windows.Forms.TabPage tabPageStock;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.DataGridView dataGridViewStockProductos;
        private System.Windows.Forms.DataGridView dataGridViewStock;
        private System.Windows.Forms.DataGridView dataGridViewStockSucursales;
        private System.Windows.Forms.TextBox textBoxProductosStock;
        private System.Windows.Forms.TextBox textBoxSucursalesStock;
    }
}

