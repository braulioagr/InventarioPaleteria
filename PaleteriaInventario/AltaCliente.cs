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
        }

        #endregion

        #region Gets&Sets

        public SqlCommand Comando
        {
            get { return this.comando; }
        }
        #endregion

        private void buttonAceptar_Click(object sender, EventArgs e)
        {
            if (!this.vacio())
            {
                this.DialogResult = DialogResult.OK;
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
