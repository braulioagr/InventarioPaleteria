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
    public partial class Categoria : Form
    {

        #region Variables de Instancia
        private int id;
        private bool alta;
        private string[] valores;
        private SqlCommand comando;
        #endregion

        #region Constructores
        public Categoria()
        {
            this.alta = true;
            valores = null;
            InitializeComponent();
        }
        public Categoria(string[] valores)
        {
            this.alta = false;
            this.valores = valores;
            InitializeComponent();
        }
        private void Categoria_Load(object sender, EventArgs e)
        {
            if (alta)
            {

                this.id = -1;
                this.comando = new SqlCommand("insert into empleado.Categoria values (@nombre,@tamaño);");
                comando.Parameters.Add(new SqlParameter("@nombre", SqlDbType.VarChar, 50));
                comando.Parameters.Add(new SqlParameter("@tamaño", SqlDbType.VarChar, 13));
            }
            else
            {

                this.id = int.Parse(valores[0]);
                this.textBoxNombre.Text = valores[1].Trim();
                this.comboBoxTamaño.Text = valores[2].Trim();
                comando = new SqlCommand("update empleado.Categoria set nombre = @nombre, tamaño = @tamaño where idCategoria = @id;");
                comando.Parameters.Add(new SqlParameter("@nombre", SqlDbType.VarChar, 50));
                comando.Parameters.Add(new SqlParameter("@tamaño", SqlDbType.VarChar, 13));
                comando.Parameters.Add(new SqlParameter("@id", SqlDbType.BigInt));
            }
        }
        #endregion

        #region Gets & Sets

        public SqlCommand Comando
        {
            get
            {
                return this.comando;
            }
        }
        #endregion

        private void buttonAceptar_Click(object sender, EventArgs e)
        {
            if (!this.vacio())
            {
                this.DialogResult = DialogResult.OK;
                if (alta)
                {
                    this.comando.Parameters[0].Value = this.textBoxNombre.Text;
                    this.comando.Parameters[1].Value = this.comboBoxTamaño.Text;
                }
                else
                {
                    this.comando.Parameters[0].Value = this.textBoxNombre.Text;
                    this.comando.Parameters[1].Value = this.comboBoxTamaño.Text;
                    this.comando.Parameters[2].Value = this.id;

                }
                this.Close();
            }
            else
            {
                MessageBox.Show("No puede haber campos vacios por favor revise", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private bool vacio()
        {
            return string.IsNullOrEmpty(this.textBoxNombre.Text) | string.IsNullOrEmpty(this.comboBoxTamaño.Text);
        }

    }
}
