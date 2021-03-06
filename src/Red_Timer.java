import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class Red_Timer extends JPanel
{

      JButton jButton;
     static int rtime = 300;
    private int minute,seconds;
    private JTextArea jLabel ;
    boolean buton_pressed = false;
    private server server;
    private int Port = 5565;
    private client client;
     boolean rTurn;
     static int r = 0 ;

    Red_Timer()
    {
        try {
            StartServerorClient();
        } catch (IOException  e) {
            e.printStackTrace();
        }
        jLabel = new JTextArea();
        Font myfont = new Font("Times",Font.ITALIC,34);
        jLabel.setBackground(Color.red);
        jLabel.setFont(myfont);
        jLabel.setForeground(Color.white);
        jButton = new JButton("Start");

        Runnable runnable1 = () -> {

            while (true) {
                if (StartScreen.temp == 1) {
                    if (buton_pressed) {
                        try {

                            server.sendOutput("" + rtime);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                } else {

                    try {


                        String string = client.getinput().nextLine();
                        rtime = Integer.parseInt(string);
                        jLabel.setText("");
                        minute = rtime / 60;
                        seconds = rtime % 60;
                        jLabel.append(minute + "-" + seconds);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(850);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        Thread time1 = new Thread(runnable1);
        time1.start();
        jButton.addActionListener(e -> {
            if (r == 0)
            {
            t.start();
                jButton.setVisible(false);
                jButton.setEnabled(false);
            r++;
            }

            buton_pressed = true;
            rTurn = true;

        });
        add(jLabel);
        jLabel.setEditable(false);

        setVisible(true);

//****************************************

//------------------------------------------------

    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //jLabel.setText(time/60 +"-"+time%60);
            while (rtime != 0) {

                if (buton_pressed) {
                    if (rTurn) {

                        jLabel.setText("");
                        minute = rtime / 60;
                        seconds = rtime % 60;
                        jLabel.append(minute + "-" + seconds);


                        rtime--;

                        try {
                            Thread.sleep(850);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        repaint();

                    }
                }


                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (Blue_Timer.btime == 0)
            {
                Score.isGameEnd = true;
                Score.gameEnd();

            }

        }
    };
    Thread t =new Thread(runnable);



    private void StartServerorClient() throws IOException {
        if (StartScreen.temp==1)
        {
            Server();
        }else
        {
            Client();
        }
    }
    private void  Server() throws IOException {
        server = new server();
        server.setServer(Port);

    }
    private void Client() throws IOException {
        client = new client();
        client.setclient(TalkScreen.Ipaddr,Port);
    }
}



