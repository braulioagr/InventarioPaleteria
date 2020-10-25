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
            this.command = new SqlCommand("select * from empleado.Venta where idVenta = " + this.idVenta.ToString());
            this.nexo.ejecutarSQL(this.command);
            this.idCliente = int.Parse(this.dataGridViewVenta.Rows[0].Cells[1].Value.ToString());
            this.command.CommandText = "select * from empleado.DetalleVenta where idVenta = " + this.idVenta.ToString();
            this.nexo.ejecutarSQL(this.command);
            this.command.CommandText = "select * from empleado.Cliente where idCliente = " + this.idCliente.ToString();
            this.nexo.ejecutarSQL(this.command);
        }
        #endregion
    }
}
