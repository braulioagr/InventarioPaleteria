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
    public partial class ModificaCliente : Form
    {
        int id;
        string[] valores;
        SqlCommand comando;
        #region Constructores

        public ModificaCliente(string[] valores)
        {
            this.valores = valores;
            InitializeComponent();
        }

        private void ModificaCliente_Load(object sender, EventArgs e)
        {
            this.id = int.Parse(valores[0]);
            this.textBoxNombre.Text = valores[1].Trim();
            this.comboBoxTipo.Text = valores[3].Trim();
            this.textBoxTelefono.Text = valores[2].Trim();
            this.numericDescuento.Value = decimal.Parse(valores[4]);
            comando = new SqlCommand();
            comando.CommandText = "update empleado.Cliente set nombreCliente = @nombre, telefono = @telefono," +
                                  "tipoCliente = @tipo, descuento = @descuento where idCliente = @id;";
            comando.Parameters.Add(new SqlParameter("@nombre", SqlDbType.VarChar, 50));
            comando.Parameters.Add(new SqlParameter("@telefono", SqlDbType.VarChar, 13));
            comando.Parameters.Add(new SqlParameter("@tipo", SqlDbType.VarChar, 7));
            comando.Parameters.Add(new SqlParameter("@descuento", SqlDbType.Real));
            comando.Parameters.Add(new SqlParameter("@id", SqlDbType.BigInt));
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
                this.comando.Parameters[4].Value = this.id;
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
