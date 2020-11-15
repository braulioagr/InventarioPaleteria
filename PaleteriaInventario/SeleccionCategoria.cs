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
    partial class SeleccionCategoria : Form
    {
        public delegate void ActualizaDataGrid(DataGridView dataGrid);
        public event ActualizaDataGrid actualizaDatagrid;
        int idCatgoria;
        public SeleccionCategoria()
        {
            InitializeComponent();
        }

        private void SeleccionCategoria_Load(object sender, EventArgs e)
        {
            this.idCatgoria = -1;
            this.actualizaDatagrid(this.dataGridViewCategoria);
        }

        private void dataGridViewProducto_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            this.idCatgoria = int.Parse(this.dataGridViewCategoria.CurrentRow.Cells[0].Value.ToString());
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (this.idCatgoria != -1)
            {
                this.DialogResult = DialogResult.OK;
                this.Close();
            }
            else
            {
                MessageBox.Show("Por favor primero seleccione un profucto", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
        }
        public int IdCatgoria
        {
            get { return this.idCatgoria; }
        }

    }
}
