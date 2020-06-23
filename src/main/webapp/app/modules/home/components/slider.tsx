import React from 'react';
import { Row, Col, Alert, Carousel, CarouselItem, CarouselCaption, CarouselIndicators, CarouselControl } from 'reactstrap';

export interface ISliderProps{
    props?: any;
}
export interface ISliderState{
    activeIndex: number;
    animating: boolean;
}
class Slider extends React.Component<ISliderProps, ISliderState>{
    constructor(props: ISliderProps) {
        super(props);
        this.state = {
            activeIndex: 0,
            animating: false
        }
    }

    render() {
        const items = [
            {
              src: 'content/images/carousel/playa2.jpg',
              altText: 'Slide 1',
              caption: 'Slide 1',
              key: '1'
            },
            {
              src: 'content/images/carousel/playa.jpg',
              altText: 'Slide 2',
              caption: 'Slide 2',
              key: '2'
            },
            {
              src: 'content/images/carousel/playa2.jpg',
              altText: 'Slide 3',
              caption: 'Slide 3',
              key: '3'
            }
        ];
        const next = () => {
            if (this.state.animating) return;
            const nextIndex = this.state.activeIndex === items.length - 1 ? 0 : this.state.activeIndex + 1;
            this.setState({ activeIndex: nextIndex });
        }

        const previous = () => {
            if (this.state.animating) return;
            const nextIndex = this.state.activeIndex === 0 ? items.length - 1 : this.state.activeIndex - 1;
            this.setState({ activeIndex: nextIndex });
        }

        const goToIndex = (newIndex) => {
            if (this.state.animating) return;
            this.setState({ activeIndex: newIndex });
        }
        const slides = items.map((item) => {
            return (
                <CarouselItem
                    onExiting={() => this.setState({ animating: true })}
                    onExited={() => this.setState({ animating: false })}
                    key={item.key}
                >
                    <img src={item.src} alt={item.altText} width='100%'/>
                    <CarouselCaption captionText={item.caption} captionHeader={item.caption} />
                </CarouselItem>
            );
        });
        return(
            <div>
                <Carousel
                    activeIndex={this.state.activeIndex}
                    next={next}
                    previous={previous}
                >
                    <CarouselIndicators items={items} activeIndex={this.state.activeIndex} onClickHandler={()=> goToIndex(event.target)} />
                    {slides}
                    <CarouselControl direction="prev" directionText="Previous" onClickHandler={() => previous()} />
                    <CarouselControl direction="next" directionText="Next" onClickHandler={() => next()} />
                </Carousel>
            </div>
        );
    }
}
export default Slider;