using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PaleteriaInventario
{
    partial class ConsultaDetalleInventario : Form
    {
        int id;
        Nexo nexo;
        public ConsultaDetalleInventario(int id, Nexo nexo)
        {
            this.id = id;
            this.nexo = nexo;
            InitializeComponent();
        }

        private void ConsultaDetalleInventario_Load(object sender, EventArgs e)
        {
            this.nexo.actualizaGrid(this.dataGridViewInventario,
                "select  distinct(i.idInventario), s.direccion, SUM(p.cantidadRecibida) as total, i.fechaRecepcion from empleado.Inventario i " +
                "inner join empleado.InventarioProducto p on i.idInventario = p.idInventario " +
                "inner join empleado.Sucursal s on s.idSucursal = i.idSucursal " +
                "where i.idInventario = " + this.id.ToString() +
                " group by i.idInventario, s.direccion,i.fechaRecepcion", "Inventario");
            this.nexo.actualizaGrid(this.dataGridViewDetalles,
                "select p.sabor, c.nombreCategoria as categoria, c.tamaño, i.cantidadRecibida from empleado.InventarioProducto i " +
                "inner join empleado.Producto p on i.idProducto = p.idProducto " +
                "inner join empleado.Categoria c on c.idCategoria = p.idCategoria " +
                "where i.idInventario = " + this.id.ToString(),
                "InventarioProducto");
        }
    }
}
