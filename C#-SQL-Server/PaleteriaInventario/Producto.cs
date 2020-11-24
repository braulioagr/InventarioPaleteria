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
    partial class Producto : Form
    {

        #region Variables de instancia
        private int id;
        private bool alta;
        private string[] valores;
        private SqlCommand comando;
        private Nexo nexo;
        #endregion

        #region Constructores
        public Producto(Nexo nexo)
        {
            this.alta = true;
            this.valores = null;
            this.nexo = nexo;
            InitializeComponent();
        }

        public Producto(Nexo nexo,string[] valores)
        {
            this.alta = false;
            this.valores = valores;
            this.nexo = nexo;
            InitializeComponent();
        }

        private void Producto_Load(object sender, EventArgs e)
        {
            if (alta)
            {
                this.id = -1;
                this.comando = new SqlCommand("insert into empleado.Producto values (@idCategoria,@idPrecio,@idSabor);");
                comando.Parameters.Add(new SqlParameter("@idCategoria", SqlDbType.BigInt));
                comando.Parameters.Add(new SqlParameter("@idPrecio", SqlDbType.Real));
                comando.Parameters.Add(new SqlParameter("@idSabor", SqlDbType.VarChar, 20));
            }
            else
            {
                this.id = int.Parse(valores[0]);
                this.textBoxSabor.Text = valores[1];
                this.textBoxCategoria.Text = valores[2];
                this.textBoxPrecio.Text = valores[5];
                comando = new SqlCommand("update empleado.Producto " +
                                         "set idCategoria = @idCategoria, Precio = @Precio " +
                                         "where idProducto = @idProducto;");
                comando.Parameters.Add(new SqlParameter("@idCategoria", SqlDbType.BigInt));
                comando.Parameters.Add(new SqlParameter("@Precio", SqlDbType.Real));
                comando.Parameters.Add(new SqlParameter("@idSabor", SqlDbType.VarChar, 20));
                comando.Parameters.Add(new SqlParameter("@idProducto", SqlDbType.BigInt));

            }
        }
        #endregion

        private void button1_Click(object sender, EventArgs e)
        {
            if (!vacio())
            {
                this.DialogResult = DialogResult.OK;
                if (alta)
                {
                    this.comando.Parameters[0].Value = int.Parse(this.textBoxCategoria.Text.Trim());
                    this.comando.Parameters[1].Value = this.textBoxPrecio.Text.Trim().Replace(".", ",");
                    this.comando.Parameters[2].Value = this.textBoxSabor.Text.Trim();
                }
                else
                {
                    this.comando.Parameters[0].Value = int.Parse(this.textBoxCategoria.Text.Trim());
                    this.comando.Parameters[1].Value = this.textBoxPrecio.Text.Trim().Replace(".",",");
                    this.comando.Parameters[2].Value = this.textBoxSabor.Text.Trim();
                    this.comando.Parameters[3].Value = this.id;

                }
                this.Close();
            }
            else
            {
                MessageBox.Show("Uno de los campos esta vacio", "Advertencia", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            SeleccionCategoria alta;
            alta = new SeleccionCategoria();
            alta.actualizaDatagrid += this.cargaCategorias;
            if (alta.ShowDialog().Equals(DialogResult.OK))
            {
                this.textBoxCategoria.Text = alta.IdCatgoria.ToString();
            }
        }

        private void cargaCategorias(DataGridView dataGrid)
        {
            this.nexo.actualizaGrid(dataGrid,"select * from empleado.Categoria", "Categoria");
        }

        private bool vacio()
        {
            return string.IsNullOrEmpty(this.textBoxCategoria.Text) |
                   string.IsNullOrEmpty(this.textBoxPrecio.Text) |
                   string.IsNullOrEmpty(this.textBoxSabor.Text);
        }


        #region Gets & Sets

        public SqlCommand Comando
        {
            get
            {
                return this.comando;
            }
        }
        #endregion

    }
}
