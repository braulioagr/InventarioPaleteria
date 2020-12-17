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
    public partial class AltaDetalle : Form
    {
        public delegate void ActualizaDataGrid(DataGridView dataGrid);
        public event ActualizaDataGrid actualizaDatagrid;
        int idStock;
        public AltaDetalle()
        {
            InitializeComponent();
        }

        private void AltaDetalle_Load(object sender, EventArgs e)
        {
            this.idStock = -1;
            this.actualizaDatagrid(this.dataGridViewProducto);
        }

        private void dataGridViewProducto_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            this.idStock = int.Parse(this.dataGridViewProducto.CurrentRow.Cells[0].Value.ToString());
            this.numericUpDown1.Maximum = decimal.Parse(this.dataGridViewProducto.CurrentRow.Cells[4].Value.ToString());
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (this.idStock != -1)
            {
                this.DialogResult = DialogResult.OK;
                this.Close();
            }
            else
            {
                MessageBox.Show("Por favor primero seleccione un producto", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
        }
        public int IdProducto
        {
            get { return this.idStock; }
        }

        public int cantidad
        {
            get { return (int)this.numericUpDown1.Value; }
        }

        private void dataGridViewProducto_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
    }
}
