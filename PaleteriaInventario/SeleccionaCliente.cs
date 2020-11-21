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
    partial class SeleccionaCliente : Form
    {

        #region Variables de instancia
        private Nexo nexo;
        private int id;
        #endregion

        #region Constructores
        public SeleccionaCliente(Nexo nexo)
        {
            this.nexo = nexo;
            InitializeComponent();
        }

        private void SeleccionaCliente_Load(object sender, EventArgs e)
        {
            this.id = -1;
            this.nexo.actualizaGrid(this.dataGridViewCliente, "select * from empleado.Cliente", "Cliente");
            MessageBox.Show("Por favor seleccione el cliente al que esta atendiendo", "Importante");
        }
        #endregion


        #region gets & sets
        public int ID
        {
            get { return this.id; }
        }
        #endregion

        private void button1_Click(object sender, EventArgs e)
        {
            string mensaje;
            if (this.id != -1)
            {
                mensaje = this.dataGridViewCliente.CurrentRow.Cells[0].Value.ToString() + " , " +
                    "\"" + this.dataGridViewCliente.CurrentRow.Cells[1].Value.ToString() + "\"";
                if (MessageBox.Show("¿Usted quiere trabajar el cliente: [" + mensaje + "]", "Advertencia", MessageBoxButtons.YesNo, MessageBoxIcon.Exclamation).Equals(DialogResult.Yes))
                {
                    this.DialogResult = DialogResult.OK;
                    this.Close();
                }
            }
            else
            {
                MessageBox.Show("Por favor seleccione una sucursal", "Advertencia", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
        }

        private void dataGridViewCliente_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            this.id = int.Parse(this.dataGridViewCliente.CurrentRow.Cells[0].Value.ToString());
        }
    }
}
