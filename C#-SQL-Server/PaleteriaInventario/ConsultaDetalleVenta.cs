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
    partial class ConsultaDetalleVenta : Form
    {
        #region Variables de instancia
        Nexo nexo;
        int idVenta;
        int idCliente;
        SqlCommand command;
        #endregion

        #region Constructores
        public ConsultaDetalleVenta(Nexo nexo, int idVenta)
        {
            this.nexo = nexo;
            this.idVenta = idVenta;
            InitializeComponent();
        }

        private void ConsultaDetalleVenta_Load(object sender, EventArgs e)
        {
            this.nexo.actualizaGrid(this.dataGridViewVenta, "select * from empleado.Venta where idVenta = " + this.idVenta.ToString(), "Venta");
            this.idCliente = int.Parse(this.dataGridViewVenta.Rows[0].Cells[1].Value.ToString());
            this.nexo.actualizaGrid(this.dataGridViewCliente, "select * from empleado.Cliente where idCliente = " + this.idCliente.ToString(), "Cliente");
            this.nexo.actualizaGrid(this.dataGridViewDetalles,
                "select d.idVenta, p.sabor, c.nombreCategoria as categoria, d.unidades, p.precio, d.subTotal from empleado.DetalleVenta d " +
                "inner join empleado.Stock s on s.idStock = d.idStock " +
                "inner join empleado.Producto p on p.idProducto = s.idProducto " +
                "inner join empleado.Categoria c on c.idCategoria = p.idCategoria " +
                "where idVenta = " + this.idVenta.ToString(), "DetalleVenta");


        }
        #endregion
    }
}
