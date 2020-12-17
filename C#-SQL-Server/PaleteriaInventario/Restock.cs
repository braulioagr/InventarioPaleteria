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
        private int[] viejosValores = {-1,-1,-1 };
        private string mensaje;
        private AltaRestock seleccionaProducto;
        private bool alta;
        public Restock(Nexo nexo, int idSucursal)
        {
            this.idSucursal = idSucursal;
            this.nexo = nexo;
            this.alta = true;
            InitializeComponent();
        }

        public Restock(Nexo nexo, int idInventario, int idSucursal)
        {
            this.idSucursal = idSucursal;
            this.nexo = nexo;
            this.idInventario = idInventario;
            this.alta = false;
            InitializeComponent();
        }

        private void Restock_Load(object sender, EventArgs e)
        {
            if (alta)
            {
                this.sqlcommand = new SqlCommand("insert into empleado.Inventario values(@idSucursal,getdate());");
                this.sqlcommand.Parameters.AddWithValue("@idSucursal", this.idSucursal);
                this.nexo.ejecutarSQL(this.sqlcommand, false);
                this.idInventario = this.nexo.obtenUltimoId("empleado.Inventario", "idInventario");
                this.nexo.actualizaGrid(this.dataGridViewInventario,
                "select idInventario, fechaRecepcion from empleado.Inventario where idInventario = " + this.idInventario.ToString(), "Inventario");
            }
            else
            {
                this.button2.Visible = false;
                this.button1.Text = "Cerrar";
                this.actualizaDataGridInventario();

            }
        }

        private void actualizaDataGridInventario()
        {

            this.nexo.actualizaGrid(this.dataGridViewInventario,
                "select  distinct(i.idInventario), s.direccion, sum(p.cantidadRecibida) as total, i.fechaRecepcion from empleado.Inventario i " +
                "inner join empleado.InventarioProducto p on i.idInventario = p.idInventario " +
                "inner join empleado.Sucursal s on s.idSucursal = i.idSucursal " +
                "where i.idInventario = " + this.idInventario.ToString() +
                "group by i.idInventario, s.direccion,i.fechaRecepcion", "Inventario");
            this.nexo.actualizaGrid(this.dataGridViewDetalles,
                 "select i.idInventario, p.idProducto, i.cantidadRecibida, p.sabor, p.precio, c.tamaño, c.nombreCategoria from empleado.InventarioProducto i " +
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
                        if (alta)
                        {
                            this.abortaReabastecimiento();
                            this.DialogResult = DialogResult.Cancel;
                            this.Close();
                        }
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

        private void toolStrip1_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {
            switch (e.ClickedItem.AccessibleName)
            {
                case "Agregar":
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
                break;
                case "Actualizar":
                    seleccionaProducto = new AltaRestock();
                    seleccionaProducto.actualizaDatagrid += this.cargaProductos;
                    if (MessageBox.Show("Usted selecciono el registro \n[" +
                        mensaje + "]¿Desea Modificarlo?", "Aviso", MessageBoxButtons.YesNo).Equals(DialogResult.Yes))
                    {
                        if (seleccionaProducto.ShowDialog().Equals(DialogResult.OK))
                        {
                            this.sqlcommand = new SqlCommand("update empleado.InventarioProducto "+
                                                             "set idProducto = @idProducto, cantidadRecibida = @cantidadRecibida " +
                                                             "where idInventario = @idInventario and idProducto = @oldIdProducto and cantidadRecibida = @oldCantidadRecibida ");

                            this.sqlcommand.Parameters.AddWithValue("@idInventario", viejosValores[0]);
                            this.sqlcommand.Parameters.AddWithValue("@oldIdProducto", viejosValores[1]);
                            this.sqlcommand.Parameters.AddWithValue("@oldCantidadRecibida", viejosValores[2]);
                            this.sqlcommand.Parameters.AddWithValue("@idProducto", seleccionaProducto.IdProducto);
                            this.sqlcommand.Parameters.AddWithValue("@CantidadRecibida", seleccionaProducto.cantidad);
                            this.nexo.ejecutarSQL(this.sqlcommand,true);

                        }
                    }
                break;
                case "Eliminar":
                    if (MessageBox.Show("Usted selecciono el registro \n[" +
                        mensaje + "]¿Desea Eliminarlo?","Aviso",MessageBoxButtons.YesNo).Equals(DialogResult.Yes))
                    {
                        this.sqlcommand = new SqlCommand("delete from empleado.InventarioProducto"+
                            " where idInventario = @idInventario and idProducto = @idProducto and cantidadRecibida = @Cantidad ;");
                        this.sqlcommand.Parameters.AddWithValue("@idInventario", this.viejosValores[0]);
                        this.sqlcommand.Parameters.AddWithValue("@idProducto", this.viejosValores[1]);
                        this.sqlcommand.Parameters.AddWithValue("@Cantidad", this.viejosValores[2]);
                        this.nexo.ejecutarSQL(this.sqlcommand, true);
                        this.actualizaDataGridInventario();
                    }
                break;
            }
            this.actualizaDataGridInventario();
        }

        private void dataGridViewStocks_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            mensaje = this.dataGridViewDetalles.CurrentRow.Cells[0].Value.ToString() + " | " +
                          this.dataGridViewDetalles.CurrentRow.Cells[1].Value.ToString() + " | " +
                          this.dataGridViewDetalles.CurrentRow.Cells[2].Value.ToString() + " | " +
                          this.dataGridViewDetalles.CurrentRow.Cells[3].Value.ToString() + " | " +
                          this.dataGridViewDetalles.CurrentRow.Cells[4].Value.ToString() + " | " +
                          this.dataGridViewDetalles.CurrentRow.Cells[5].Value.ToString() + " | " +
                          this.dataGridViewDetalles.CurrentRow.Cells[6].Value.ToString();
            this.viejosValores[0] = int.Parse(this.dataGridViewDetalles.CurrentRow.Cells[0].Value.ToString());
            this.viejosValores[1] = int.Parse(this.dataGridViewDetalles.CurrentRow.Cells[1].Value.ToString());
            this.viejosValores[2] = int.Parse(this.dataGridViewDetalles.CurrentRow.Cells[2].Value.ToString());


        }
    }
}
