using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PaleteriaInventario
{
    partial class Restock : Form
    {
        private int idSucursal;
        private int idInventario;
        private Nexo nexo;
        private SqlCommand sqlcommand;
        public Restock(Nexo nexo, int idSucursal)
        {
            this.idSucursal = idSucursal;
            this.nexo = nexo;
            InitializeComponent();
        }

        private void Restock_Load(object sender, EventArgs e)
        {
            this.sqlcommand = new SqlCommand("insert into empleado.Inventario values(@idSucursal,getdate());");
            this.sqlcommand.Parameters.AddWithValue("@idSucursal", this.idSucursal);
            this.nexo.ejecutarSQL(this.sqlcommand,false);
            this.idInventario = this.nexo.obtenUltimoId("empleado.Inventario", "idInventario");
            this.nexo.actualizaGrid(this.dataGridViewInventario,
                "select  distinct(i.idInventario), s.direccion as Sucursal, i.fechaRecepcion from empleado.Inventario i " +
                "inner join empleado.Sucursal s on s.idSucursal = i.idSucursal " +
                "where i.idInventario = " +this.idInventario.ToString()+
                "group by i.idInventario, s.direccion,i.fechaRecepcion", "Inventario");
        }

        private void actualizaDataGridInventario()
        {
            this.nexo.actualizaGrid(this.dataGridViewInventario,
                "select  distinct(i.idInventario), s.direccion, sum(p.cantidadRecibida) as total, i.fechaRecepcion from empleado.Inventario i " +
                "inner join empleado.InventarioProducto p on i.idInventario = p.idInventario " +
                "inner join empleado.Sucursal s on s.idSucursal = i.idSucursal " +
                "where i.idInventario = " + this.idInventario.ToString() +
                "group by i.idInventario, s.direccion,i.fechaRecepcion", "Inventario");
            this.nexo.actualizaGrid(this.dataGridViewStocks,
                 "select p.sabor, c.nombreCategoria as categoria, c.tamaño, i.cantidadRecibida as cantidad from empleado.InventarioProducto i " +
                 "inner join empleado.Producto p on i.idProducto = p.idProducto " +
                 "inner join empleado.Categoria c on p.idCategoria = c.idCategoria " +
                 "where i.idInventario = " + this.idInventario.ToString(), "InventarioProducto");
        }

        private void opciones_Click(object sender, EventArgs e)
        {
            switch (((Button)sender).AccessibleName)
            {
                case "Aceptar":
                    this.DialogResult = DialogResult.OK;
                    this.Close();
                    break;
                case "Cancelar":
                    if(MessageBox.Show("¿Esta seguro de querer cancelar el procedimiento?", "Advertencia",MessageBoxButtons.YesNo,MessageBoxIcon.Exclamation).Equals(DialogResult.Yes))
                    {

                        this.abortaReabastecimiento();
                        this.DialogResult = DialogResult.Cancel;
                        this.Close();
                    }
                break;
            }
        }
        private void cargaProductos(DataGridView dataGrid)
        {
            this.nexo.actualizaGrid(dataGrid,
                "select p.idProducto, p.sabor, c.nombreCategoria as categoria, c.tamaño from empleado.Producto p "+
                "inner join empleado.Categoria c on p.idCategoria = c.idCategoria","Producto");
        }

        private void abortaReabastecimiento()
        {
            this.sqlcommand = new SqlCommand("delete from empleado.InventarioProducto where idInventario = @idInventario;");
            this.sqlcommand.Parameters.AddWithValue("@idInventario", this.idInventario);
            this.nexo.ejecutarSQL(this.sqlcommand, false);
            this.sqlcommand = new SqlCommand("delete from empleado.Inventario where idInventario = @idInventario;");
            this.sqlcommand.Parameters.AddWithValue("@idInventario", this.idInventario);
            this.nexo.ejecutarSQL(this.sqlcommand, false);
        }

        private void toolStripButton1_Click(object sender, EventArgs e)
        {
            AltaRestock seleccionaProducto;
            seleccionaProducto = new AltaRestock();
            seleccionaProducto.actualizaDatagrid += this.cargaProductos;
            if (seleccionaProducto.ShowDialog().Equals(DialogResult.OK))
            {
                this.sqlcommand = new SqlCommand("insert into empleado.InventarioProducto values(@idInventario,@idProducto,@Cantidad);");
                this.sqlcommand.Parameters.AddWithValue("@idInventario", this.idInventario);
                this.sqlcommand.Parameters.AddWithValue("@idProducto", seleccionaProducto.IdProducto);
                this.sqlcommand.Parameters.AddWithValue("@Cantidad", seleccionaProducto.cantidad);
                this.nexo.ejecutarSQL(this.sqlcommand, true);
                this.actualizaDataGridInventario();
            }
        }

        private void toolStrip1_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {

        }
    }
}
