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
    public partial class AltaCliente : Form
    {
        #region Variables de Instancia
        private SqlCommand comando;

        #endregion

        #region Constructores
        public AltaCliente()
        {
            InitializeComponent();
        }

        private void AltaCliente_Load(object sender, EventArgs e)
        {
            comando = new SqlCommand();
            comando.CommandText = "insert into empleado.Cliente (nombreCliente, telefono,tipoCliente,descuento) " +
           "values (@nombre, @telefono, @tipo, @descuento)";

            comando.Parameters.Add(new SqlParameter("@nombre", SqlDbType.VarChar, 50));
            comando.Parameters.Add(new SqlParameter("@telefono", SqlDbType.VarChar, 13));
            comando.Parameters.Add(new SqlParameter("@tipo", SqlDbType.VarChar, 7));
            comando.Parameters.Add(new SqlParameter("@descuento", SqlDbType.Real));
        }

        #endregion

        #region Gets&Sets

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
                this.comando.Parameters[0].Value = this.textBoxNombre.Text;
                this.comando.Parameters[1].Value = this.textBoxTelefono.Text;
                this.comando.Parameters[2].Value = this.comboBoxTipo.Text;
                this.comando.Parameters[3].Value = this.numericDescuento.Value;
                this.Close();
            }
            else
            {
                MessageBox.Show("No puede haber campos vacios por favor revise", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private bool vacio()
        {
            return string.IsNullOrEmpty(this.textBoxNombre.Text) | string.IsNullOrEmpty(this.textBoxTelefono.Text) | string.IsNullOrEmpty(this.comboBoxTipo.Text);
        }
    }
}
