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
    public partial class AltaCliente : Form
    {
        public AltaCliente()
        {
            InitializeComponent();
        }

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
